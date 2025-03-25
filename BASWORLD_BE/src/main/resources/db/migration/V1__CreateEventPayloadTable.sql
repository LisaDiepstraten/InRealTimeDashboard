CREATE TABLE bas_world_application
(
    id          INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    version     VARCHAR(50) NOT NULL,
    environment VARCHAR(50) NOT NULL
);

CREATE TABLE hibernate_sequences
(
    sequence_name VARCHAR(255) NOT NULL,
    next_val      BIGINT       NOT NULL,
    PRIMARY KEY (sequence_name)
);

INSERT INTO hibernate_sequences (sequence_name, next_val)
VALUES ('event_sequence', 1);


CREATE TABLE user
(
    id           INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id      VARCHAR(50) NOT NULL,
    email        VARCHAR(50) NOT NULL,
    person_id    VARCHAR(50) NOT NULL,
    person_email VARCHAR(50) NOT NULL,
    company_id   VARCHAR(50) NOT NULL,
    company_name VARCHAR(50) NOT NULL
);

CREATE TABLE page
(
    id                   INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title                VARCHAR(255)  NOT NULL,
    url                  VARCHAR(2048) NOT NULL,
    canonical_url        VARCHAR(2048) NOT NULL,
    design_size_enum_num INT           NOT NULL
);

CREATE TABLE vendor_clicks_id
(
    id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    google   VARCHAR(255) NOT NULL,
    bing     VARCHAR(255) NOT NULL,
    facebook VARCHAR(255) NOT NULL,
    linkedin VARCHAR(255) NOT NULL
);

CREATE TABLE utm_parameters
(
    id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    source   VARCHAR(255) NOT NULL,
    medium   VARCHAR(255) NOT NULL,
    campaign VARCHAR(255) NOT NULL,
    term     VARCHAR(255) NOT NULL,
    content  VARCHAR(255) NOT NULL
);

CREATE TABLE session
(
    id                INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    butt_session_id   VARCHAR(255) NOT NULL,
    referer           VARCHAR(255) NOT NULL,
    vendor_clicks_id  INT          NOT NULL,
    utm_parameters_id INT          NOT NULL,
    FOREIGN KEY (vendor_clicks_id) REFERENCES vendor_clicks_id (id),
    FOREIGN KEY (utm_parameters_id) REFERENCES utm_parameters (id)
);

CREATE TABLE vendor_client_ids
(
    id               INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    google_analytics VARCHAR(100),
    bing             VARCHAR(100),
    facebook         VARCHAR(100)
);

CREATE TABLE device
(
    id            INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type          VARCHAR(100),
    vendor        VARCHAR(100),
    model         VARCHAR(100),
    screen_width  INT,
    screen_height INT
);

CREATE TABLE engine
(
    id      INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(100),
    version VARCHAR(100)
);

CREATE TABLE geolocation
(
    id        INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    latitude  VARCHAR(100),
    longitude VARCHAR(100),
    country   VARCHAR(100),
    region    VARCHAR(100),
    city      VARCHAR(100)
);

CREATE TABLE operating_system
(
    id      INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(100),
    version VARCHAR(100)
);

CREATE TABLE browser
(
    id                   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name                 VARCHAR(100),
    version              VARCHAR(100),
    viewport_width       INT,
    viewport_height      INT,
    language             VARCHAR(100),
    cookies_enabled      BOOLEAN,
    javascript_enabled   BOOLEAN,
    do_not_track_enabled BOOLEAN
);

CREATE TABLE client
(
    id                   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    butt_client_id       VARCHAR(100),
    raw_user_agent       VARCHAR(255),
    vendor_client_ids_id INT NOT NULL,
    device_id            INT NOT NULL,
    operating_system_id  INT NOT NULL,
    browser_id           INT NOT NULL,
    engine_id            INT NOT NULL,
    geolocation_id       INT NOT NULL,
    FOREIGN KEY (vendor_client_ids_id) REFERENCES vendor_client_ids (id),
    FOREIGN KEY (device_id) REFERENCES device (id),
    FOREIGN KEY (operating_system_id) REFERENCES operating_system (id),
    FOREIGN KEY (browser_id) REFERENCES browser (id),
    FOREIGN KEY (engine_id) REFERENCES engine (id),
    FOREIGN KEY (geolocation_id) REFERENCES geolocation (id)
);



CREATE TABLE event_headers
(
    id              INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    entity          VARCHAR(50) NOT NULL,
    entity_key      VARCHAR(50) NOT NULL,
    event_main_type VARCHAR(50) NOT NULL,
    event_sub_type  VARCHAR(50) NOT NULL,
    time_stamp      TIMESTAMP   NOT NULL,
    published_by    VARCHAR(50) NOT NULL,
    policy_version  VARCHAR(50) NOT NULL
);

CREATE TABLE context
(
    id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    page_id    INT NOT NULL,
    session_id INT NOT NULL,
    client_id  INT NOT NULL,
    user_id    INT NOT NULL,
    FOREIGN KEY (page_id) REFERENCES page (id),
    FOREIGN KEY (session_id) REFERENCES session (id),
    FOREIGN KEY (client_id) REFERENCES client (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);


/*CREATE TABLE view_item_list_event
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    event_name      VARCHAR(50)  NOT NULL,
    additional_data JSON,
    item_list_id    VARCHAR(255) NOT NULL,
    item_list_name  VARCHAR(255) NOT NULL
);
*/


/*
-- Junction table to support the many-to-many relationship
CREATE TABLE view_item_list_event_items
(
    view_item_list_event_id INT NOT NULL,
    item_id                 INT NOT NULL,
    PRIMARY KEY (view_item_list_event_id, item_id),
    FOREIGN KEY (view_item_list_event_id) REFERENCES view_item_list_event (id),
    FOREIGN KEY (item_id) REFERENCES item (id)
);
CREATE TABLE element_interaction_event
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    event_name       VARCHAR(50)  NOT NULL,
    additional_data  JSON,
    element_name     VARCHAR(255) NOT NULL,
    interaction_type VARCHAR(255) NOT NULL
);

CREATE TABLE element_visibility_event
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    event_name      VARCHAR(50)  NOT NULL,
    additional_data JSON,
    element_name    VARCHAR(255) NOT NULL
);

CREATE TABLE page_visit_event
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    event_name      VARCHAR(50)  NOT NULL,
    additional_data JSON,
    page_type       VARCHAR(255) NOT NULL,
    page_language   VARCHAR(255) NOT NULL
);

CREATE TABLE select_item_event
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    event_name      VARCHAR(50)  NOT NULL,
    additional_data JSON,
    item_list_id    VARCHAR(255) NOT NULL,
    item_list_name  VARCHAR(255) NOT NULL,
    item_id         INT          NOT NULL,
    FOREIGN KEY (item_id) REFERENCES item (id) -- Reference to item table
);


CREATE TABLE other_event
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    event_name      VARCHAR(50) NOT NULL,
    additional_data JSON
);


*/

-- Create events table without foreign key
CREATE TABLE events
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    event_name       VARCHAR(50) NOT NULL,
    additional_data  JSON,
    item_list_id     VARCHAR(255), -- For view_item_list_event and select_item_event
    item_list_name   VARCHAR(255), -- For view_item_list_event and select_item_event
    item_id          INT,          -- For select_item_event (assuming this links to an item table)
    element_name     VARCHAR(255), -- For element_interaction_event and element_visibility_event
    interaction_type VARCHAR(255), -- For element_interaction_event
    page_type        VARCHAR(255), -- For page_visit_event
    page_language    VARCHAR(255), -- For page_visit_event
    event_type       VARCHAR(50) NOT NULL
);

-- Create item table without foreign key
CREATE TABLE item
(
    id             INT            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    item_id        INT            NOT NULL,
    item_name      VARCHAR(50)    NOT NULL,
    item_brand     VARCHAR(50)    NOT NULL,
    affiliation    VARCHAR(50)    NOT NULL,
    item_category  VARCHAR(50)    NOT NULL,
    item_category2 VARCHAR(50)    NOT NULL,
    item_list_id   VARCHAR(50)    NOT NULL,
    item_list_name VARCHAR(50)    NOT NULL,
    item_index     INT            NOT NULL,
    item_variant   VARCHAR(50)    NOT NULL,
    price          DECIMAL(10, 2) NOT NULL,
    quantity       INT            NOT NULL
);

-- Now alter tables to add foreign key constraints
ALTER TABLE item
    ADD view_item_list_event_id INT,
ADD FOREIGN KEY (view_item_list_event_id) REFERENCES events(id); -- Add foreign key to events

ALTER TABLE events
    ADD FOREIGN KEY (item_id) REFERENCES item (id); -- Add foreign key to item


CREATE TABLE event_payload
(
    id             INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    application_id INT NOT NULL,
    context_id     INT NOT NULL,
    event_id       INT NOT NULL,
    FOREIGN KEY (application_id) REFERENCES bas_world_application (id),
    FOREIGN KEY (context_id) REFERENCES context (id),
    FOREIGN KEY (event_id) REFERENCES events (id)
);

CREATE TABLE bas_world_event
(
    id               INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    event            VARCHAR(50) NOT NULL,
    event_payload_id INT         NOT NULL,
    event_header_id  INT         NOT NULL,
    FOREIGN KEY (event_payload_id) REFERENCES event_payload (id),
    FOREIGN KEY (event_header_id) REFERENCES event_headers (id)
);
