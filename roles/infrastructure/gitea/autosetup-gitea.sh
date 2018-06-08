#!/bin/bash

echo Waiting for gitea to start

function is_gitea_running {
  echo checking whether gitea is running
  local http_code=$(curl http://localhost:3000 -w '%{http_code}' -s -o /tmp/null)
  if [[ $http_code -ne '404' && $http_code -ne '000' ]]; then
    return 0
  else
    return 1
  fi
}

function is_gitea_installed {
  echo Checking whether gitea is running
  curl -v http://localhost:3000 2>&1 -o /tmp/null | grep -q -F 'Location: /install'
  if [[ $? -eq 1 ]]; then
    return 0
  else
    return 1
  fi
}

function gitea_initial_setup {
  curl 'http://0.0.0.0:3000/install'\
    -o /tmp/null\
    -H 'Content-Type: application/x-www-form-urlencoded'\
    -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8' \
    -H 'Connection: keep-alive'\
    --data 'db_type=SQLite3&db_host=mysql%3A3306&db_user=&db_passwd=&db_name=&ssl_mode=disable&db_path=%2Fdata%2Fgitea%2Fgitea.db&app_name=Endava+TechFlow+Gitea&repo_root_path=%2Fdata%2Fgit%2Frepositories&run_user=git&domain=localhost&ssh_port=22&http_port=3000&app_url=http%3A%2F%2Flocalhost%3A3000%2F&log_root_path=%2Fdata%2Fgitea%2Flog&smtp_host=&smtp_from=&smtp_email=&smtp_passwd=&admin_name=administrator&admin_passwd=admin123&admin_confirm_passwd=admin123&admin_email=fake-email%40.endava.net'\
    --compressed
}

function gitea_create_cd_repo {

  giteatoken=`curl -s -X POST http://localhost:3000/api/v1/users/administrator/tokens -H 'authorization: Basic YWRtaW5pc3RyYXRvcjphZG1pbjEyMw==' -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{"name": "cd-practice-token"}'`
  token=`echo $giteatoken | cut -f3 -d':' | cut -f2 -d'"'`

  curl -X POST "http://localhost:3000/api/v1/user/repos?access_token=$token" -H "accept: application/json" -H "content-type: application/json" -d "{\"name\":\"techflow-frontend\"}"
  curl -X POST "http://localhost:3000/api/v1/user/repos?access_token=$token" -H "accept: application/json" -H "content-type: application/json" -d "{\"name\":\"techflow-backend\"}"
  
  #curl -v -u administrator:admin123 -X POST http://localhost:3000/api/v1/admin/users/administrator/repos -d name=cd-app -d private=false
}

#function gitea_add_ssh_key {
#    curl -v -u administrator:admin123
#    -X POST http://localhost:3000/api/v1/admin/users/administrator/keys
#    -d title=test
#    --data-urlencode key="ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQClc2mHxOfk6KQaZZG7lMOnkMZXcxygXvZPmZrNtBRuS2JGsI8NhB1WmoULzd8Nb2JCv6ewrlTe60TJHk2bmfukkjxpJwYdDmHk9TzjwMmByT6GTQ9x60wjDab+9qv2Iqq3Wa+yOQ1k7fdcATgDeUU0oH72/rswdsNC3KL88louf4rApx89ryRD4Gn2psdQKSZJQDKl6VEyGP8YgW1cuCqyEbU12giWxyNXPV4244ZNDEsL/i/fG55mdPX9UVrlebDDO346Q3fDqWjm+EkBaRZcV0XVDtSzZIxjI3K9LUauVA1yeQ6Tn7MApdNuqUCoL4iPT5JnlOMUjD2BUx5lHMkJ aenache@EN617138"
#}

until is_gitea_running; do
  echo Gitea still not running, waiting 5 seconds
  sleep 5
done
echo Finally Gitea is running


gitea_initial_setup && echo "Successfully setup Gitea" || echo "Error setting up Gitea"
gitea_create_cd_repo && echo "Successfully created the cd-app Git repository" || echo "Error setting up the cd-app git repo"

until is_gitea_installed; do
  echo Gitea still not setup, waiting 5 seconds
  sleep 5
done
echo Finally Gitea is setup

#Tell s6 to not restart me
s6-svc -d /etc/s6/autosetup-gitea
