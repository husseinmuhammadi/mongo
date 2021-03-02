# MongoDB

MongoDB is a cross-platform, document oriented database that provides, high performance, high availability, and easy scalability. MongoDB works on concept of collection and document.

## Database
Database is a physical container for collections. Each database gets its own set of files on the file system. A single MongoDB server typically has multiple databases.

## Collection 
Collection is a group of MongoDB documents. It is the equivalent of an RDBMS table. A collection exists within a single database. Collections do not enforce a schema. Documents within a collection can have different fields. Typically, all documents in a collection are of similar or related purpose.

## Documents 
A document is a set of key-value pairs. Documents have dynamic schema. Dynamic schema means that documents in the same collection do not need to have the same set of fields or structure, and common fields in a collection's documents may hold different types of data.

```
show dbs
db
db.products.insert({“name”:”hamer”})
db.dropDatabase();
db.createCollection(name, options)
show collections
db.COLLECTION_NAME.drop()
db.COLLECTION_NAME.insert(document)
db.COLLECTION_NAME.insert([documents])
db.COLLECTION_NAME.save(document)
```

If you don't specify _id in the document then save() method will work same as insert() method. If you specify _id then it will replace whole data of document containing _id as specified in save() method.

db.COLLECTION_NAME.insertOne(document)
db.COLLECTION_NAME.insertMany([documents])
db.COLLECTION_NAME.find()
db.COLLECTION_NAME.find().pretty()
db.COLLECTION_NAME.findOne()
db.COLLECTION_NAME.find({<key1>:<value1>})
db.mycol.find({ $and: [ {<key1>:<value1>}, { <key2>:<value2>} ] })
db.mycol.find({$or: [{key1: value1}, {key2:value2}]}).pretty()








_id is 12 bytes hexadecimal number unique for every document in a collection. 12 bytes are divided as follows −
_id: ObjectId(4 bytes timestamp, 3 bytes machine id, 2 bytes process id, 3 bytes incrementer)





