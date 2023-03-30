create table IF NOT EXISTS PRODUCTS (id integer not null, content varchar(255), primary key (id));

-- which allows us to universally identify any principle or authority in the system
-- SID: which is the username or role name. SID stands for Security Identity
-- PRINCIPAL: 0 or 1, to indicate that the corresponding SID is a principal (user, such as mary, mike, jack…) or an authority (role, such as ROLE_ADMIN, ROLE_USER, ROLE_EDITOR…)

CREATE TABLE IF NOT EXISTS acl_sid (
  id bigint NOT NULL AUTO_INCREMENT,
  principal tinyint NOT NULL,
  sid varchar(100) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT unique_uk_1 UNIQUE (sid,principal)
);

-- store class name of the domain object,
CREATE TABLE IF NOT EXISTS acl_class (
  id bigint NOT NULL AUTO_INCREMENT,
  class varchar(255) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT unique_uk_2 UNIQUE (class)
);


-- ACL_ENTRY store individual permission assigns to each SID on an Object Identity:

-- ID
-- ACL_OBJECT_IDENTITY: specify the object identity, links to ACL_OBJECT_IDENTITY table
-- ACE_ORDER: the order of current entry in the ACL entries list of corresponding Object Identity
-- SID: the target SID which the permission is granted to or denied from, links to ACL_SID table
-- MASK: the integer bit mask that represents the actual permission being granted or denied
-- GRANTING: value 1 means granting, value 0 means denying
-- AUDIT_SUCCESS and AUDIT_FAILURE: for auditing purpose

CREATE TABLE IF NOT EXISTS acl_entry (
  id bigint NOT NULL AUTO_INCREMENT,
  acl_object_identity bigint NOT NULL,
  ace_order int NOT NULL,
  sid bigint NOT NULL,
  mask int NOT NULL,
  granting tinyint NOT NULL,
  audit_success tinyint NOT NULL,
  audit_failure tinyint NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT unique_uk_4 UNIQUE (acl_object_identity,ace_order)
);

-- ACL_OBJECT_IDENTITY, which stores information for each unique domain object:

-- ID
-- OBJECT_ID_CLASS: define the domain object class, links to ACL_CLASS table
-- OBJECT_ID_IDENTITY: domain objects can be stored in many tables depending on the class. Hence, this field store the target object primary key
-- PARENT_OBJECT: specify parent of this Object Identity within this table
-- OWNER_SID: ID of the object owner, links to ACL_SID table
-- ENTRIES_INHERITING: whether ACL Entries of this object inherits from the parent object (ACL Entries are defined in ACL_ENTRY table)

CREATE TABLE IF NOT EXISTS acl_object_identity (
  id bigint NOT NULL AUTO_INCREMENT,
  object_id_class bigint NOT NULL,
  object_id_identity bigint NOT NULL,
  parent_object bigint DEFAULT NULL,
  owner_sid bigint DEFAULT NULL,
  entries_inheriting tinyint NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT unique_uk_3 UNIQUE (object_id_class,object_id_identity)
);

ALTER TABLE acl_entry
ADD FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity(id);

ALTER TABLE acl_entry
ADD FOREIGN KEY (sid) REFERENCES acl_sid(id);

--
-- Constraints for table acl_object_identity
--
ALTER TABLE acl_object_identity
ADD FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id);

ALTER TABLE acl_object_identity
ADD FOREIGN KEY (object_id_class) REFERENCES acl_class (id);

ALTER TABLE acl_object_identity
ADD FOREIGN KEY (owner_sid) REFERENCES acl_sid (id);