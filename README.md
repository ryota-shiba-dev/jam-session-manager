## Jam Session Manager (ジャムセッション管理システム)

## 概要 / Overview
自身の趣味であるジャムセッションの活動記録を管理・検索するためのデータベースプロジェクトです。 

This is a database project to manage and search for my personal jam session activity records.

## 開発の背景 / Background
ジャムセッションの演奏記録（日時、場所、演奏した曲、感想）が散乱していたため、一括でデータ登録を行い、後から特定の条件で振り返りができるようにしたいと考え、本システムを設計しました。 

I designed this system to consolidate scattered jam session records (date, location, songs played, and impressions) into a database for easy searching and review.

## 技術スタック / Tech Stack
Database: PostgreSQL (Public Schema)

Design Tool: DBeaver

Language: SQL

## ディレクトリ構成 / Directory Structure
```text
.
└── DB
    └──DDL
        ├── jam_sessions.sql  -- セッション全体を管理する親テーブル
        └── session_songs.sql  -- 演奏曲を管理する子テーブル（1対多のリレーション）
```


## 設計のこだわり / Design Key Points

### 1. テーブルの正規化 (Normalization)
「1つのセッションで複数の曲を演奏する」という実態に合わせ、テーブルを「セッション情報(`jam_sessions`)」と「曲情報(`session_songs`)」の2つに分離しました。これにより、データの重複を排除し、管理をしやすくしています。

I normalized the database into two tables: `jam_sessions` and `session_songs`. This allows for a one-to-many relationship where one session can include multiple songs, preventing data redundancy.

### 2. データの整合性 (Data Integrity)
`session_id` に外部キー制約（FOREIGN KEY）を設定し、セッションが削除された場合には関連する曲データも自動で削除されるよう `ON DELETE CASCADE` を設定しました。

I used a Foreign Key with `ON DELETE CASCADE` for `session_id` to ensure data consistency and automatic cleanup of related records.

### 3. 管理用カラムの追加 (Audit Columns)
実務のシステム設計を意識し、データがいつ登録されたかを自動記録する `created_at` カラムをすべてのテーブルに持たせています。

I included a `created_at` column in all tables to automatically track when data is registered, following professional system design practices.
