# ArticleRestAPI
Basic Article Rest API

Java 11

DB used - mySQL

connection properties in -> src.main.resources.application.properties

Article SQL:

idarticle - int (primary key, auto_increment)

author - varchar(45)

magazine_Title - varchar(45)

publication_Date - date

release_Body - longtext

release_Title - varchar(45)

timestamp - timestamp(6)


Endpoints:

Get "/articles" - returning a List of all articles ordered desc by Date

Get "articles/{id}" - returning an Article with specified path variable id

Get "articles/keyword={keyword} - returning a list of all artictles that contain a keyword in its release

Post "/articles" - requests body of an article and creates the article

Put "/articles/{id}" - requests body to update an existing Article with specified path variable id

Delete "articles/{id}" - deletes an existing Article with specified path variable id


Contains Controller tests
