use springpdb;

-- drop table if exists springpdb.task;
-- drop table if exists springpdb.account;
-- drop table if exists springpdb.habit;
-- drop table if exists springpdb.annual_goal;
-- drop table if exists springpdb.monthly_goal;
-- drop table if exists springpdb.member;

CREATE table springpdb.member(
	member_id bigint auto_increment PRIMARY KEY,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    profile_image varchar(255),
    profile_content varchar(300),
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
	updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
);

CREATE table springpdb.account(
	account_id bigint auto_increment PRIMARY KEY,
	email varchar(100) UNIQUE,
    password varchar(100),
    member_id bigint,
    role varchar(10) NOT NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
	updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    FOREIGN KEY(member_id) references springpdb.member(member_id) ON DELETE CASCADE
);

CREATE table springpdb.task(
	task_id bigint auto_increment PRIMARY KEY,
    content varchar(100) NOT NULL,
    success boolean,
    start_time time,
    end_time time,
    task_date date,
    member_id bigint NOT NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
	updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    FOREIGN KEY(member_id) references springpdb.member(member_id)
);

CREATE table springpdb.habit(
	habit_id bigint auto_increment PRIMARY KEY,
    content varchar(100) NOT NULL,
    #postive or negative
    habit_type varchar(10) NOT NULL,
    member_id bigint NOT NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
	updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    FOREIGN KEY(member_id) references springpdb.member(member_id)
);

CREATE table springpdb.annual_goal(
	annual_goal_id bigint auto_increment PRIMARY KEY,
    category varchar(100) NOT NULL,
    content varchar(100) NOT NULL,
    member_id bigint NOT NULL,
    year int NOT NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
	updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    FOREIGN KEY(member_id) references springpdb.member(member_id)
);

CREATE table springpdb.monthly_goal(
	monthly_goal_id bigint auto_increment PRIMARY KEY,
    content varchar(100) NOT NULL,
    member_id bigint NOT NULL,
    date Date NOT NULL,
    parent_goal_id bigint,
    completed BOOLEAN default false, 
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
	updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    FOREIGN KEY(member_id) references springpdb.member(member_id),
    FOREIGN KEY(parent_goal_id) references springpdb.annual_goal(annual_goal_id)
);

CREATE table springpdb.diary(
	diary_id bigint auto_increment PRIMARY KEY,
    title varchar(100) NOT NULL,
    content varchar(3000) NOT NULL,
    member_id bigint NOT NULL,
    date Date NOT NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
	updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    FOREIGN KEY(member_id) references springpdb.member(member_id)
);