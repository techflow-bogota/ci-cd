#!/usr/bin/env bash

echo waiting for nexus to start | tee -a /tmp/hayoo

function is_nexus_running {
  echo checking whether nexus is running | tee -a /tmp/hayoo
  local http_code=$(curl -w '%{http_code}' -o /tmp/curl_debug -s -XGET -u admin:admin123 http://localhost:8081/service/siesta/rest/v1/script)
  if [[ $http_code -eq 200 ]]; then
    return 0
  else
    return 1
  fi
}

function create_docker_repo {
  curl -v -X POST -u admin:admin123 --header "Content-Type: application/json" 'http://localhost:8081/service/siesta/rest/v1/script' -d @/opt/sonatype/nexus/create-docker-repo.json 2>&1 | tee -a /tmp/hayoo
  curl -v -X POST -u admin:admin123 --header "Content-Type: text/plain" 'http://localhost:8081/service/siesta/rest/v1/script/create-techflow-docker-repo/run'
}


until is_nexus_running; do
  echo nexus still not running, waiting 5 seconds | tee -a /tmp/hayoo
  sleep 5
done

echo finally nexus is running | tee -a /tmp/hayoo

create_docker_repo && echo "Successfully created docker repo" || echo "Error creating docker repo"
