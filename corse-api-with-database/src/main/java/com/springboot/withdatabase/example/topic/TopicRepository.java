package com.springboot.withdatabase.example.topic;

import org.springframework.data.repository.CrudRepository;

/*CrudRepository takes generic types first argument is the entity class which we are working i.e., 
  the class which contains map to the database fields, second argument is the data type of the Id
*/
public interface TopicRepository extends CrudRepository<Topic, String> {
	
	 

}
