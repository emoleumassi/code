# code

curl -H 'Content-Type: application/json' -d '@/PATH-TO/test.json' -X POST http://HOST:8080/thesis-0.1/webservices --> POST Webservice

curl -H 'Content-Type: application/json' -X GET http://HOST:8080/thesis-0.1/webservices/ --> GET all Webservice

curl -H 'Content-Type: application/json' -X GET http://HOST:8080/thesis-0.1/webservices/services/{serviceName} --> GET all service by name

curl -H 'Content-Type: application/json' -X GET http://HOST:8080/thesis-0.1/webservices/{uddislaId} --> GET Webservice by uddislaId

curl -H 'Content-Type: application/json' -X GET http://HOST:8080/thesis-0.1/webservices/{uddislaName} --> GET Webservice by Webservicenamen

curl -H 'Content-Type: application/json'  -X GET http://HOST:8080/thesis-0.1/webservices/{uddislaId}/uddi --> GET UDDI by uddislaId

curl -H 'Content-Type: application/json'  -X GET http://HOST:8080/thesis-0.1/webservices/{uddislaId}/sla --> GET SLA by uddislaId

curl -H 'Content-Type: application/json'  -X GET http://HOST:8080/thesis-0.1/webservices/{uddislaId}/sla/services/{serviceTermId} --> GET Service by serviceTermId

curl -H 'Content-Type: application/json'  -X DELETE http://HOST:8080/thesis-0.1/webservices/{uddislaId}: --> DELETE Webservice by uddislaId

