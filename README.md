# code

curl -H 'Content-Type: application/json' -d '@/PATH-TO/test.json' -X POST http://HOST:8080/thesis-0.1/webservices --> POST Webservice

curl -H 'Content-Type: application/json' -X GET http://HOST:8080/thesis-0.1/webservices/ --> GET alle Webservice

curl -H 'Content-Type: application/json' -X GET http://HOST:8080/thesis-0.1/webservices/{uddislaId} --> GET Webservice with uddislaId

curl -H 'Content-Type: application/json' -X GET http://HOST:8080/thesis-0.1/webservices/{uddislaName} --> GET Webservice with Webservicenamen

curl -H 'Content-Type: application/json'  -X GET http://HOST:8080/thesis-0.1/webservices/{uddislaId}/uddi --> GET UDDI with uddislaId

curl -H 'Content-Type: application/json'  -X GET http://HOST:8080/thesis-0.1/webservices/{uddislaId}/sla --> GET SLA with uddislaId

curl -H 'Content-Type: application/json'  -X GET http://HOST:8080/thesis-0.1/webservices/{uddislaId}/sla/services/{serviceTermId} --> GET Service with serviceTermId

curl -H 'Content-Type: application/json'  -X DELETE http://HOST:8080/thesis-0.1/webservices/{uddislaId}: --> DELETE Webservice with uddislaId

