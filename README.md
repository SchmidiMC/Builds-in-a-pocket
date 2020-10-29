# Builds-in-a-pocket
A Spigot based plugin for minecraft.

## Getting started
### 1. Clone the project into your workspace:

- ``git clone https://github.com/SchmidiMC/Builds-in-a-pocket.git``

This is a maven project so you need to update the project to download the dependencies.

### 2. Maven Build

- goals:
  - clean
  - compile
  - package

- parameters:
  - Name: ``mcCommonOutputDir`` - Value: ``{OutputDirectory}``
  
  ### 3. Local Server
  
  - Download the ``spigot.jar`` on the correct version for your plugin.
    - https://getbukkit.org/download/spigot
    - www.spigotmc.org/wiki/buildtools/ (to generate the jar yourself)
    
 - You need to execute the jar now. I am using an ``.batch`` file for that.
 - My example:
     ```
    @echo off

    :restart
    java -Xms1G -Xmx1G -jar spigot-1.16.3.jar

    goto restart
    ```
 - When you execute it where will be an ``eula.txt`` file generated. You need to open it, change it's value to ``true`` and restart your server.
 - It should generate now all folders.

- Now you can paste your plugin the ``plugins`` folder and restart or reload the server.
 

