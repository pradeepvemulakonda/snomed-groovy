# snomed-groovy
Groovy script to load snomed data into a neo4j container

Create a simple to setup - set of docker container to populate snomed-ct data into the Neo4j Database.

This code does the following

1) Read the snomed description file and create a mapping between the concepts and
  # FSN
  # Synonym
  # Description
  
2) Create a list of Relationship types. To be used to add labels for relationships between nodes.
3) In the initial run create concepts with FSA
4) In the second run update all Synonyms and descriptions onto the existing concepts.
5) In the third run setup the relationships between the concepts.
6) Each of the Concept and Relationship will be indexed both on the description text as well as the Label.
7) Concepts will have a "Concept" label.
8) Relationship will have a "Relationship" label.

*MVP:*
Get a Neo4j Database populated with SNOMED-CT international version.

Nice to have:
Update uploaded nodes with Reference sets data.

*Next Release:*
Restful services to access SNOMED-CT data
  # Medications Resource
  # Problems List Resource
  # Allergies Resource
  # Diseases Resource
  
Also Map ICD to Snomed-Ct and LOINC to Snomed-CT expressions.  

For a Python version of the same please have a look at https://github.com/pradeepvemulakonda/Snomed

For more information on SNOMED-CT and understand about Medical Terminology go to  http://www.vemulakonda.com/2017/05/understanding-snomed-ct-data-model.html



