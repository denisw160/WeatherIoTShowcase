# Internet-of-Things-Server

## Installation
First install VirtualBox and create a default linux machine for Ubuntu. 
The machine should have at least 4 GB of RAM. For storage you should add a 50 GB 
hard drive. For installation of Ubuntu add an optical drive for the installation medium.

Add a NAT network interface and configure the the following rules:

| Application           | Host-Port     | Guest-Port    |
| --------------------- | ------------- | ------------- |
| HTTPD                 | 80            | 80            |
| MARIADB               | 3306          | 3306          |
| MQTT                  | 1883          | 1883          |
| MQTTS                 | 8883          | 8883          |
| SSH                   | 2222          | 22            |
| TOMCAT                | 8080          | 8080          |

Now you can download the Ubuntu Server from https://www.ubuntu.com/download/server. Mount 
the image in the virtual machine and power up. Run the installer with your favorite setting.
Add the OpenSSH Server to your installation. 

### Preparation
After install connect to the server via SSH.
Login in the server (via ssh) with your admin account and execute the following steps: 

1. Disable SUDO password question

    ```bash
    $ sudo visudo -f /etc/sudoers.d/sysadm
    ```
    and add the following line
    ``` 
    sysadm  ALL=NOPASSWD:ALL
    ```

1. Install Ansible on the server
    
    ```bash
    $ sudo apt-get update
    $ sudo apt-get install software-properties-common
    $ sudo apt-add-repository ppa:ansible/ansible
    $ sudo apt-get update
    $ sudo apt-get install ansible
    ```

1. Setup the ssh key for the server
    
    ```bash
    $ mkdir ~/.ssh
    $ chmod 700 ~/.ssh
    $ ssh-keygen -t rsa
	```
    
    then copy the ssh-key to remote server
    ```bash
    $ ssh-copy-id sysadm@localhost
    ```

1. Adding the host to Ansible hosts (optional)
    ```bash
    $ sudo nano /etc/ansible/hosts
    ```
    by add the following lines
    ```
    # IoT Servers
    [showcase]
    localhost
    ```

1. Testing the configuration (optional)
    ```bash
    $ ansible showcase -m ping
    ```
    The result look like that
    ```
    localhost | SUCCESS => {
        "changed": false,
        "ping": "pong"
    }
    ```
    
## Setup

Now the server is prepared for setup with Ansible.
This directory must be transferred to the server and then 
execute the Ansible playbook.
 

1. Copy the playbooks on the server and start the setup
    ```bash
    $ ansible-playbook -i production main.yml
    ```

1. Verify the open ports
    ```bash
    $ sudo netstat -tulpn
    ```

## Customizing

If you want to customize the installation all Ansible variables are stored in
group_vars.
