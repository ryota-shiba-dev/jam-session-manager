##Jam Session Manager (ジャムセッション管理システム)

##概要 / Overview
自身の趣味であるジャムセッションの活動記録を管理・検索するためのデータベースプロジェクトです。 This is a database project to manage and search for my personal jam session activity records.

##開発の背景 / Background
ジャムセッションの演奏記録（日時、場所、演奏した曲、感想）が散乱していたため、一括でデータ登録を行い、後から特定の条件で振り返りができるようにしたいと考え、本システムを設計しました。 I designed this system to consolidate scattered jam session records (date, location, songs played, and impressions) into a database for easy searching and review.

##技術スタック / Tech Stack
Database: PostgreSQL (Public Schema)
Design Tool: DBeaver
Language: SQL

##ディレクトリ構成 / Directory Structure
Plaintext
.
└── db/
    ├── jam_sessions.sql  -- セッション全体を管理する親テーブル
    └── session_songs.sql  -- 演奏曲を管理する子テーブル（1対多のリレーション）
