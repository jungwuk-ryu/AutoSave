# Nukkit Auto Save Plugin
![tmp1](https://user-images.githubusercontent.com/29895665/107823902-24dd7980-6db3-11eb-8c58-a78248b023a4.png)  
#### Please back up your server before using this plugin.
This plugin will save all plugins on your server without restarting server.  

## How is it works
- It will detect "save" method on plugin and invoke it.    
- You can add a plugin from config.yml to invoke_OnDisable if your plugin saves when the server stops.

## Command
```
/as save - save instantly
/as reload - reload config.yml
```

## Monitoring
![image](https://user-images.githubusercontent.com/29895665/107825303-6f5ff580-6db5-11eb-901c-b840db24776d.png)  
You can monitor by setting debug to true in config.yml 

 