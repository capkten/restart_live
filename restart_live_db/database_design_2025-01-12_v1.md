erDiagram
	current_status ||--o{ users : references
	final_aim ||--o{ users : references
	review_type ||--o{ users : references
	review_record ||--o{ users : references
	review_record ||--o{ review_type : references
	review_record_comment ||--o{ review_record : references
	review_record_comment ||--o{ users : references
	tasks ||--o{ users : references
	tasks_index ||--o{ tasks : references
	tasks_comment ||--o{ users : references
	tasks_comment ||--o{ tasks : references
	award_shop ||--o{ users : references
	exchange_record ||--o{ award_shop : references
	exchange_record ||--o{ users : references
	life_list ||--o{ users : references

	users {
		UUID id
		VARCHAR(30) username
		VARCHAR(32) password
		DECIMAL((10,2)) money
		DATE birthday
		DECIMAL((10,2)) stars
		DECIMAL((10,2)) used_stars
		TIMESTAMP create_time
		TIMESTAMP update_time
		BOOLEAN is_delete
		VARCHAR(255) avatar
		INTEGER level
		BIGINT experience
	}

	current_status {
		UUID id
		UUID user_id
		VARCHAR(255) content
		TIMESTAMP create_time
		TIMESTAMP update_time
		BOOLEAN is_delete
	}

	final_aim {
		UUID id
		UUID user_id
		VARCHAR(255) content
		TIMESTAMP create_time
		TIMESTAMP update_time
		BOOLEAN is_delete
		BOOLEAN is_finished
	}

	review_type {
		UUID id
		UUID user_id
		VARCHAR(255) name
		INTEGER sort
		BOOLEAN is_delete
		TIMESTAMP create_time
		TIMESTAMP update_time
	}

	review_record {
		UUID id
		UUID user_id
		UUID rt_id
		BOOLEAN is_delete
		TEXT content
		TIMESTAMP create_time
		TIMESTAMP update_time
	}

	review_record_comment {
		UUID id
		UUID user_id
		VARCHAR(255) username
		TEXT comment
		TIMESTAMP create_time
		TIMESTAMP update_time
		BOOLEAN is_delete
		UUID rr_id
		VARCHAR(20) file_type
		VARCHAR(255) file_url
	}

	tasks {
		UUID id
		UUID user_id
		SMALLINT type
		VARCHAR(255) content
		BOOLEAN is_finished
		TIMESTAMP create_time
		TIMESTAMP update_time
		TIMESTAMP finish_time
		TEXT remark
		INTEGER starts
		INTEGER experience
	}

	tasks_index {
		INTEGER id
		UUID t_id
		INTEGER starts
		VARCHAR(255) content
		TIMESTAMP create_time
		TIMESTAMP update_time
		BOOLEAN is_delete
		BOOLEAN is_finish
		TIMESTAMP finish_time
		INTEGER experience
	}

	tasks_comment {
		UUID id
		UUID user_id
		VARCHAR(255) username
		TEXT comment
		TIMESTAMP create_time
		TIMESTAMP update_time
		UUID t_id
		VARCHAR(20) file_type
		VARCHAR(255) file_url
		BOOLEAN is_delete
	}

	award_shop {
		UUID id
		UUID user_id
		VARCHAR(255) name
		TEXT introduce
		VARCHAR(255) cover_img
		INTEGER num
		BOOLEAN is_delete
		TIMESTAMP create_time
		TIMESTAMP update_time
		INTEGER starts
	}

	exchange_record {
		UUID id
		UUID as_id
		UUID user_id
		TIMESTAMP create_time
		TIMESTAMP update_time
		BOOLEAN is_delete
		INTEGER stars
		VARCHAR(255) as_name
		VARCHAR(255) as_cover_url
		TEXT as_introduce
	}

	life_list {
		UUID id
		UUID user_id
		VARCHAR(255) content
		BOOLEAN is_delete
		TIMESTAMP create_time
		TIMESTAMP update_time
		BOOLEAN is_finish
	}