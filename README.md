# snomed-groovy
Groovy script to load snomed data into a neo4j container

> Prerequistes: You need to install
* Neo4j at port location: "bolt://localhost:7687", userName: "neo4j", password: "neo4j or what ever you have".Change it in ApplicationProperties
* Install elastic search at the default port

As Neo4j does not index the text, we are using elastic search to index the text and use the conceptId to zero in on a node in Neo4j. Once we and on a node we can navigate tthrough the graph to get the required data and relationships.

Create a simple to setup - set of docker container to populate snomed-ct data into the Neo4j Database.

This code does the following

1) Read the snomed description file and create a mapping between the concepts and
   1) FSN
   2) Synonym
   3) Description
  
2) Create a list of Relationship types. To be used to add labels for relationships between nodes.
3) In the initial run create concepts with FSA, list of synoyms and descriptions.
4) In the third run setup the relationships between the concepts.
5) Each of the Concept and Relationship will be indexed both on the description text as well as the Label.
6) Concepts will have a "Concept" label.
7) Relationship will have a specific relationship label.
8) Use elastic search to make the nodes and relationships searchable by text.

# MVP:
Get a Neo4j Database populated with SNOMED-CT international version. (/)

# Nice to have:
Add an automcomplete, fuzzy search functionality => Created an Elastic search instance with neo4j data.
Update uploaded nodes with Reference sets data.

# Next Release:
Restful services to access SNOMED-CT data
  1) Medications Resource
  2) Problems List Resource
  3) Allergies Resource
  4) Diseases Resource
  
Also Map ICD to Snomed-Ct and LOINC to Snomed-CT expressions.  

For a Python version of the same please have a look at https://github.com/pradeepvemulakonda/Snomed

For more information on SNOMED-CT and understand about Medical Terminology go to  http://www.vemulakonda.com/2017/05/understanding-snomed-ct-data-model.html



