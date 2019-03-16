CREATE TABLE item
(
    id                              NUMBER GENERATED ALWAYS AS IDENTITY,
    description  VARCHAR(255)       NULL,
    completed                       SMALLINT      NULL,
    listId                          NUMBER
);


CREATE TABLE list
(
    id                              NUMBER NOT NULL AUTO_INCREMENT,
    name                            VARCHAR(255)       NULL,
    PRIMARY KEY (id)
);