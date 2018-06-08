# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
config.vm.synced_folder ".", "/vagrant/endava-techflow/", type: "rsync",
rsync__exclude: ".git/"
#rsync__exclude: "app/",
#rsync__exclude: ['.git/*', 'app/']
  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://atlas.hashicorp.com/search.
#  config.vm.box = "base"
config.vm.box = "ubuntu/xenial64"
config.vm.provision :shell, path: "bootstrap.sh"

config.vm.network "forwarded_port", guest: 3001, host: 3001 # gitea
config.vm.network "forwarded_port", guest: 3000, host: 3000 # frontend
config.vm.network "forwarded_port", guest: 5000, host: 5000 # nexus connector
config.vm.network "forwarded_port", guest: 8080, host: 8080 # jenkins
config.vm.network "forwarded_port", guest: 8081, host: 8081 # nexus UI
config.vm.network "forwarded_port", guest: 8082, host: 8082 # app backend

  # Enable provisioning with a shell script. Additional provisioners such as
  # Puppet, Chef, Ansible, Salt, and Docker are also available. Please see the
  # documentation for more information about their specific syntax and use.
  # config.vm.provision "shell", inline: <<-SHELL
  #   apt-get update
  #   apt-get install -y apache2
  # SHELL
config.ssh.insert_key = false

config.vm.provision "ansible_local" do |ansible|
  ansible.verbose = "v"
  ansible.playbook = "endava-techflow/playbook.yml"
  end
  config.vm.provider "virtualbox" do |vb|
      vb.memory = "4092"
  end
end
