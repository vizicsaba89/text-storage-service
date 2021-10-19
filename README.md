# text-storage-service
betvictor text-storage-service

Additional informations:
For kafka communication i've used the bitnami/kafka helm chart (helm install betvictor-kafka bitnami/kafka)
For postgres communication i've used the bitnami/postgresql helm chart through requirements.yaml
The service are also compatible with helm
You can start the service with skaffold if it's more convenient (skaffold dev --port-forward)

Version informations:
java: 11
helm: v3.5.3
skaffold: v1.21.0
k8s: v1.19.3

Install / deployment example:
helm install betvictor-kafka bitnami/kafka

from text-storage-service root -> skaffold dev --port-forward -> "http://localhost:{exposed-port}/betvictor/history" endpoint available
