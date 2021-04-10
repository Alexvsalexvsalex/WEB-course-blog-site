# Blog web-site

Login, register are supported.

Users have different permission roles: guest, registered, writer, admin.
Writers and admins can create posts. Registered users are allowed to read and comment them.

Service uses Spring framework with Hibernate for MySQL.
Template engine - Freemarker.
Database holds passwords as a Sha1 hash.

Require setting up database data in `src/main/resources/application.properties`
