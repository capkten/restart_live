# database_design documentation
## Summary

- [Introduction](#introduction)
- [Database Type](#database-type)
- [Table Structure](#table-structure)
	- [users](#users)
	- [current_status](#current_status)
	- [final_aim](#final_aim)
	- [review_type](#review_type)
	- [review_record](#review_record)
	- [review_record_comment](#review_record_comment)
	- [tasks](#tasks)
	- [tasks_index](#tasks_index)
	- [tasks_comment](#tasks_comment)
	- [award_shop](#award_shop)
	- [exchange_record](#exchange_record)
	- [life_list](#life_list)
	- [runtime_task](#runtime_task)
	- [runtime_task_template](#runtime_task_template)
	- [runtime_task_engine](#runtime_task_engine)
	- [projects](#projects)
	- [modules](#modules)
	- [income_tag](#income_tag)
	- [expenditure_tag](#expenditure_tag)
	- [money_pay_record](#money_pay_record)
- [Relationships](#relationships)
- [Database Diagram](#database-Diagram)

## Introduction

## Database type

- **Database system:** PostgreSQL
## Table structure

### users

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **username** | VARCHAR(30) | not null  |  | |
| **password** | VARCHAR(32) | not null  |  | |
| **money** | DECIMAL(10,2) | not null  |  | |
| **birthday** | DATE | not null  |  | |
| **stars** | DECIMAL(10,2) | not null  |  | |
| **used_stars** | DECIMAL(10,2) | not null  |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **is_delete** | BOOLEAN | not null  |  | |
| **avatar** | VARCHAR(255) | not null  |  | |
| **level** | INTEGER | not null  |  | |
| **experience** | BIGINT | not null  |  | |
| **version** | INTEGER | not null  |  | |
| **save_money** | DECIMAL(10,2) | not null  |  | | 


### current_status

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **user_id** | UUID | not null  | current_status_user_id_fk | |
| **content** | VARCHAR(255) | not null  |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **is_delete** | BOOLEAN | not null  |  | |
| **version** | INTEGER | not null  |  | | 


### final_aim

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **user_id** | UUID | not null  | final_aim_user_id_fk | |
| **content** | VARCHAR(255) | not null  |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **is_delete** | BOOLEAN | not null  |  | |
| **is_finished** | BOOLEAN | not null  |  | |
| **version** | INTEGER | not null  |  | | 


### review_type

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **user_id** | UUID | not null  | review_type_user_id_fk | |
| **name** | VARCHAR(255) | not null  |  | |
| **sort** | INTEGER | not null , default: 10 |  | |
| **is_delete** | BOOLEAN | not null  |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **version** | INTEGER | not null  |  | | 


### review_record
复盘记录
| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **user_id** | UUID | not null  | review_record_user_id_fk | |
| **rt_id** | UUID | not null  | review_record_rt_id_fk | |
| **is_delete** | BOOLEAN | not null  |  | |
| **content** | TEXT | not null  |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **version** | INTEGER | not null  |  | | 


### review_record_comment

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **user_id** | UUID | not null  | review_record_comment_user_id_fk | |
| **username** | VARCHAR(255) | not null  |  | |
| **comment** | TEXT | not null  |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **is_delete** | BOOLEAN | not null  |  | |
| **rr_id** | UUID | not null  | review_record_comment_rr_id_fk | |
| **file_type** | VARCHAR(20) | not null  |  | |
| **file_url** | VARCHAR(255) | not null  |  | |
| **version** | INTEGER | not null  |  | | 


### tasks

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **user_id** | UUID | not null  | tasks_user_id_fk | |
| **type** | SMALLINT | not null  |  | |
| **content** | VARCHAR(255) | not null  |  | |
| **is_finished** | BOOLEAN | not null  |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **finish_time** | TIMESTAMP | not null  |  | |
| **remark** | TEXT | not null  |  | |
| **starts** | DECIMAL(10,2) | not null  |  | |
| **experience** | INTEGER | not null  |  | |
| **version** | INTEGER | not null  |  | | 


### tasks_index

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | INTEGER | 🔑 PK, not null , unique, autoincrement |  | |
| **t_id** | UUID | not null  | tasks_index_t_id_fk | |
| **starts** | INTEGER | not null  |  | |
| **content** | VARCHAR(255) | not null  |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **is_delete** | BOOLEAN | not null  |  | |
| **is_finish** | BOOLEAN | not null  |  | |
| **finish_time** | TIMESTAMP | not null  |  | |
| **experience** | INTEGER | not null  |  | |
| **version** | INTEGER | not null  |  | | 


### tasks_comment

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **user_id** | UUID | not null  | tasks_comment_user_id_fk | |
| **username** | VARCHAR(255) | not null  |  | |
| **comment** | TEXT | not null  |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **t_id** | UUID | not null  | tasks_comment_t_id_fk | |
| **file_type** | VARCHAR(20) | not null  |  | |
| **file_url** | VARCHAR(255) | not null  |  | |
| **is_delete** | BOOLEAN | not null  |  | |
| **version** | INTEGER | not null  |  | | 


### award_shop

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **user_id** | UUID | not null  | award_shop_user_id_fk | |
| **name** | VARCHAR(255) | not null  |  | |
| **introduce** | TEXT | not null  |  | |
| **cover_img** | VARCHAR(255) | not null  |  | |
| **num** | INTEGER | not null  |  | |
| **is_delete** | BOOLEAN | not null  |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **starts** | INTEGER | not null  |  | |
| **version** | INTEGER | not null  |  | | 


### exchange_record

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **as_id** | UUID | not null  | exchange_record_as_id_fk | |
| **user_id** | UUID | not null  | exchange_record_user_id_fk | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **is_delete** | BOOLEAN | not null  |  | |
| **stars** | INTEGER | not null  |  | |
| **as_name** | VARCHAR(255) | not null  |  | |
| **as_cover_url** | VARCHAR(255) | not null  |  | |
| **as_introduce** | TEXT | not null  |  | |
| **version** | INTEGER | not null  |  | | 


### life_list

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **user_id** | UUID | not null  | life_list_user_id_fk | |
| **content** | VARCHAR(255) | not null  |  | |
| **is_delete** | BOOLEAN | not null  |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **is_finish** | BOOLEAN | not null  |  | |
| **version** | INTEGER | not null  |  | | 


### runtime_task

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **name** | VARCHAR(255) | not null  |  | |
| **remark** | TEXT | not null  |  | |
| **stars** | DECIMAL(10,2) | not null  |  | |
| **belonging_time** | TIMESTAMP | not null  |  | |
| **is_finish** | BOOLEAN | not null  |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **version** | INTEGER | not null  |  | |
| **uid** | UUID | not null  | runtime_task_uid_fk | |
| **is_delete** | BOOLEAN | not null  |  | |
| **create_way** | INTEGER | not null  |  | |
| **external_id** | VARCHAR(255) | not null  |  | | 


### runtime_task_template

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **uid** | UUID | not null  | runtime_task_template_uid_fk | |
| **name** | VARCHAR(255) | not null  |  | |
| **version** | INTEGER | not null  |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **remark** | TEXT | not null  |  | |
| **stars** | DECIMAL(10,2) | not null  |  | |
| **belonging_time** | VARCHAR(255) | not null  |  | |
| **is_delete** | BOOLEAN | not null  |  | |
| **version** | INTEGER | not null  |  | | 


### runtime_task_engine

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **uid** | UUID | not null  | runtime_task_engine_uid_fk | |
| **is_delete** | TIMESTAMP | not null  |  | |
| **rtt_id** | UUID | not null  | runtime_task_engine_rtt_id_fk | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **version** | INTEGER | not null  |  | |
| **belonging_time_way** | INTEGER | not null  |  | |
| **belonging_time_str** | VARCHAR(255) | not null  |  | | 


### projects

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **uid** | UUID | not null  | projects_uid_fk | |
| **name** | VARCHAR(255) | not null  |  | |
| **introduce** | TEXT | not null  |  | |
| **is_finish** | BOOLEAN | not null  |  | |
| **is_delete** | BOOLEAN | not null  |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **version** | INTEGER | not null  |  | |
| **stars** | DECIMAL(10,2) | not null  |  | |
| **belonging_time** | TIMESTAMP | not null  |  | |
| **start_time** | TIMESTAMP | not null  |  | | 


### modules

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **pid** | UUID | not null  | modules_pid_fk | |
| **last_id** | UUID | not null  |  | |
| **name** | VARCHAR(255) | not null  |  | |
| **introduce** | TEXT | not null  |  | |
| **is_finish** | BOOLEAN | not null  |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **is_delete** | BOOLEAN | not null  |  | |
| **version** | INTEGER | not null  |  | |
| **stars** | DECIMAL(10,2) | not null  |  | |
| **belonging_time** | TIMESTAMP | not null  |  | |
| **start_time** | TIMESTAMP | not null  |  | |
| **modueles_type** | INTEGER | not null  |  | | 


### income_tag

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **name** | VARCHAR(255) | not null  |  | |
| **user_id** | UUID | not null  | income_tag_uid_fk | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **version** | INTEGER | not null  |  | |
| **is_delete** | BOOLEAN | not null  |  | | 


### expenditure_tag

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **name** | VARCHAR(255) | not null  |  | |
| **user_id** | UUID | not null  | expenditure_tag_user_id_fk | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **version** | INTEGER | not null  |  | |
| **is_delete** | BOOLEAN | not null  |  | | 


### money_pay_record

| Name        | Type          | Settings                      | References                    | Note                           |
|-------------|---------------|-------------------------------|-------------------------------|--------------------------------|
| **id** | UUID | 🔑 PK, not null , unique |  | |
| **create_time** | TIMESTAMP | not null  |  | |
| **update_time** | TIMESTAMP | not null  |  | |
| **is_delete** | BOOLEAN | not null  |  | |
| **version** | INTEGER | not null  |  | |
| **tag_ids** | VARCHAR(2000) | not null  |  | |
| **way** | INTEGER | not null  |  | |
| **user_id** | UUID | not null  | money_pay_record_user_id_fk | |
| **name** | VARCHAR(255) | not null  |  | |
| **remark** | TEXT | not null  |  | | 


## Relationships

- **current_status to users**: many_to_one
- **final_aim to users**: many_to_one
- **review_type to users**: many_to_one
- **review_record to users**: many_to_one
- **review_record to review_type**: many_to_one
- **review_record_comment to review_record**: many_to_one
- **review_record_comment to users**: many_to_one
- **tasks to users**: many_to_one
- **tasks_index to tasks**: many_to_one
- **tasks_comment to users**: many_to_one
- **tasks_comment to tasks**: many_to_one
- **award_shop to users**: many_to_one
- **exchange_record to award_shop**: many_to_one
- **exchange_record to users**: many_to_one
- **life_list to users**: many_to_one
- **runtime_task to users**: many_to_one
- **runtime_task_template to users**: many_to_one
- **runtime_task_engine to users**: many_to_one
- **runtime_task_engine to runtime_task_template**: many_to_one
- **projects to users**: many_to_one
- **modules to projects**: one_to_one
- **income_tag to users**: many_to_one
- **expenditure_tag to users**: many_to_one
- **money_pay_record to users**: many_to_one

## Database Diagram

```mermaid
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
	runtime_task ||--o{ users : references
	runtime_task_template ||--o{ users : references
	runtime_task_engine ||--o{ users : references
	runtime_task_engine ||--o{ runtime_task_template : references
	projects ||--o{ users : references
	modules ||--|| projects : references
	income_tag ||--o{ users : references
	expenditure_tag ||--o{ users : references
	money_pay_record ||--o{ users : references

	users {
		UUID id
		VARCHAR(30) username
		VARCHAR(32) password
		DECIMAL(10,2) money
		DATE birthday
		DECIMAL(10,2) stars
		DECIMAL(10,2) used_stars
		TIMESTAMP create_time
		TIMESTAMP update_time
		BOOLEAN is_delete
		VARCHAR(255) avatar
		INTEGER level
		BIGINT experience
		INTEGER version
		DECIMAL(10,2) save_money
	}

	current_status {
		UUID id
		UUID user_id
		VARCHAR(255) content
		TIMESTAMP create_time
		TIMESTAMP update_time
		BOOLEAN is_delete
		INTEGER version
	}

	final_aim {
		UUID id
		UUID user_id
		VARCHAR(255) content
		TIMESTAMP create_time
		TIMESTAMP update_time
		BOOLEAN is_delete
		BOOLEAN is_finished
		INTEGER version
	}

	review_type {
		UUID id
		UUID user_id
		VARCHAR(255) name
		INTEGER sort
		BOOLEAN is_delete
		TIMESTAMP create_time
		TIMESTAMP update_time
		INTEGER version
	}

	review_record {
		UUID id
		UUID user_id
		UUID rt_id
		BOOLEAN is_delete
		TEXT content
		TIMESTAMP create_time
		TIMESTAMP update_time
		INTEGER version
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
		INTEGER version
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
		DECIMAL(10,2) starts
		INTEGER experience
		INTEGER version
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
		INTEGER version
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
		INTEGER version
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
		INTEGER version
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
		INTEGER version
	}

	life_list {
		UUID id
		UUID user_id
		VARCHAR(255) content
		BOOLEAN is_delete
		TIMESTAMP create_time
		TIMESTAMP update_time
		BOOLEAN is_finish
		INTEGER version
	}

	runtime_task {
		UUID id
		VARCHAR(255) name
		TEXT remark
		DECIMAL(10,2) stars
		TIMESTAMP belonging_time
		BOOLEAN is_finish
		TIMESTAMP create_time
		TIMESTAMP update_time
		INTEGER version
		UUID uid
		BOOLEAN is_delete
		INTEGER create_way
		VARCHAR(255) external_id
	}

	runtime_task_template {
		UUID id
		UUID uid
		VARCHAR(255) name
		INTEGER version
		TIMESTAMP create_time
		TIMESTAMP update_time
		TEXT remark
		DECIMAL(10,2) stars
		VARCHAR(255) belonging_time
		BOOLEAN is_delete
		INTEGER version
	}

	runtime_task_engine {
		UUID id
		UUID uid
		TIMESTAMP is_delete
		UUID rtt_id
		TIMESTAMP create_time
		TIMESTAMP update_time
		INTEGER version
		INTEGER belonging_time_way
		VARCHAR(255) belonging_time_str
	}

	projects {
		UUID id
		UUID uid
		VARCHAR(255) name
		TEXT introduce
		BOOLEAN is_finish
		BOOLEAN is_delete
		TIMESTAMP create_time
		TIMESTAMP update_time
		INTEGER version
		DECIMAL(10,2) stars
		TIMESTAMP belonging_time
		TIMESTAMP start_time
	}

	modules {
		UUID id
		UUID pid
		UUID last_id
		VARCHAR(255) name
		TEXT introduce
		BOOLEAN is_finish
		TIMESTAMP create_time
		TIMESTAMP update_time
		BOOLEAN is_delete
		INTEGER version
		DECIMAL(10,2) stars
		TIMESTAMP belonging_time
		TIMESTAMP start_time
		INTEGER modueles_type
	}

	income_tag {
		UUID id
		VARCHAR(255) name
		UUID user_id
		TIMESTAMP create_time
		TIMESTAMP update_time
		INTEGER version
		BOOLEAN is_delete
	}

	expenditure_tag {
		UUID id
		VARCHAR(255) name
		UUID user_id
		TIMESTAMP create_time
		TIMESTAMP update_time
		INTEGER version
		BOOLEAN is_delete
	}

	money_pay_record {
		UUID id
		TIMESTAMP create_time
		TIMESTAMP update_time
		BOOLEAN is_delete
		INTEGER version
		VARCHAR(2000) tag_ids
		INTEGER way
		UUID user_id
		VARCHAR(255) name
		TEXT remark
	}
```