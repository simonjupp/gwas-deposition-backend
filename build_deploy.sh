#!/bin/bash

mvn clean install

cd gdapp-backend-service

docker build --force-rm=true -t gwas-deposition-backend:latest .
docker tag gwas-deposition-backend:latest ebispot/gwas-deposition-backend:latest-sandbox
docker push ebispot/gwas-deposition-backend:latest-sandbox

cd ..

kubectl delete deploy gwas-deposition-backend
kubectl create -f kubernetes/gwas-deposition-backend-deployment.yaml
