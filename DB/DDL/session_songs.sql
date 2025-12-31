-- 演奏曲子テーブル
DROP TABLE IF EXISTS public.session_songs;
CREATE TABLE public.session_songs (
    id SERIAL PRIMARY KEY,
    session_id INTEGER REFERENCES jam_sessions(id) ON DELETE CASCADE,
    song_name VARCHAR(255) NOT NULL,
    youtube_url TEXT,
    song_review TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- データを取り込んだ日時（自動設定）
);