# text-storage-service
betvictor text-storage-service

Additional informations:

For kafka communication i've used the bitnami/kafka helm chart (helm install betvictor-kafka bitnami/kafka) <br />
For postgres communication i've used the bitnami/postgresql helm chart through requirements.yaml <br />
The service is also compatible with helm <br />
You can start the service with skaffold if it's more convenient (skaffold dev --port-forward) <br />

Version informations:

java: 11 <br />
helm: v3.5.3 <br />
skaffold: v1.21.0 <br />
k8s: v1.19.3 <br />

Install / deployment example:

helm install betvictor-kafka bitnami/kafka <br />
from text-storage-service root -> skaffold dev --port-forward -> "http://localhost:{exposed-port}/betvictor/history" endpoint available <br />
