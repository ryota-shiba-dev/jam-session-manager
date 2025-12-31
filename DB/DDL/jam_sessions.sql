-- セッション親テーブル
DROP TABLE IF EXISTS public.jam_sessions;
CREATE OR REPLACE TABLE public.jam_sessions (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    session_date TIMESTAMP NOT NULL, -- 実際の演奏日時
    location VARCHAR(100),
    review TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- データを取り込んだ日時（自動設定）
);