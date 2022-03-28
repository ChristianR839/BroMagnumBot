# BroMagnumBot

Bro Magnum is a Discord bot that utilizes the JDA (Java Discord API) (https://github.com/DV8FromTheWorld/JDA). Its purpose is to provide WPI CS4401 students with a flag given they direct message (DM) the bot while it is online and certain conditions are met.

*Requirements*
• Java Runtime Environment 1.8.0 or higher (https://www.java.com/en/download/manual.jsp)

*Installation/Use*
1.  Acquire the BroMagnum.jar file (not found in this repository)

2.  Open the BroMagnum.jar file with Java Runtime Environment

3.  When opened, Bro Magnum will connect to Discord servers and appear online
      
      3a. There may be a scenario where this no longer works. Discord bots each have a unique token and, should that token be reset either manually or automatically, your version of the application will cease to function. If this happens, contact @ChristianR839 for a new BroMagnum.jar.
      
      3b. The current token for Bro Maagnum cannot be found online as Discord will automatically reset its token, should it be discovered on the internet.

4.  While the application is open, the bot is online and will remain that way until interrupted.
      
      4a. The console output is viewable in the 'Console Out' text area. There is no option for input.
          
          • When the following message appears, the bot has successfully connected to the Discord servers:
            `[JDA MainWS-ReadThread] INFO JDA - Finished Loading!`
      
      4b. There are two buttons on the bottom right of the window.
          
          • 'Restart - Will disconnect the bot from the Discord servers and reconnect it automatically.
          
          • 'Close' - Will disconnect the bot from the Discord servers and close the application.
      
      4c. There is a list of commands on the bottom left of the window that can be used in a direct message (on Discord) with this bot.
          
          • '!ping' - Will ping the bot and return its response time.
          
          • '!restart_bro' - Will restart the bot remotely, without the need to use the application.

5. Any action that closes the application will disconnect the bot from the Discord servers.
