use biomed;

create table uploaded_data (
    id int auto_increment primary key,
    sample_id int not null,
    data_type varchar(100) not null, -- 数据类型，例如 drug, drug_label, dosing_guideline
    content longtext not null,      -- 文件内容
    created_at datetime default current_timestamp,
    foreign key (sample_id) references sample(id)
);

ALTER TABLE sample AUTO_INCREMENT = 1;
SET @max_id = (SELECT MAX(id) FROM sample);
ALTER TABLE sample AUTO_INCREMENT = @max_id + 1;
ALTER TABLE uploaded_data AUTO_INCREMENT = 1;
SET @max_id = (SELECT MAX(id) FROM sample);
ALTER TABLE uploaded_data AUTO_INCREMENT = @max_id + 1;

DELETE FROM sample WHERE id IN (9);

SET FOREIGN_KEY_CHECKS = 0;
UPDATE biomed.sample t SET t.id = 1 WHERE t.id = 10;
UPDATE biomed.sample t SET t.id = 2 WHERE t.id = 11;

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE sample;
DROP TABLE uploaded_data;