# BroMagnumBot

Bro Magnum is a Discord bot that utilizes the JDA (Java Discord API) (https://github.com/DV8FromTheWorld/JDA). Its purpose is to provide WPI CS4401 students with a flag given they direct message (DM) the bot while it is online and certain conditions are met

***Requirements***

• Java Runtime Environment 1.8.0 or higher (https://www.java.com/en/download/manual.jsp)

• IntelliJ IDEA 2021.3.3 or higher (https://www.jetbrains.com/idea/download/) or any other Java IDE of choice

***Creating a Discord Bot***

1.  Navigate to the Discord Developer Portal > Applications page (https://discord.com/developers/applications). This will require logging in with a Discord account. If you do not have one, create one to proceed
      
2.  Select the "New Application" button at the top right of the window next to your user icon

![image](https://user-images.githubusercontent.com/46659572/160954773-57cc11c0-a218-44f6-975f-f5c218da374c.png)

3.  Create a new application, giving it any name you choose. This can be changed later

![image](https://user-images.githubusercontent.com/46659572/160954873-4cbf1e61-27f9-4b5d-bfc0-56bee27cedcc.png)

4.  Your application has been created, but it is not a bot just yet. Navigate to the "Bot" tab on the left side of the window

![image](https://user-images.githubusercontent.com/46659572/160955005-81d1558f-2690-48c2-9dcc-53335423543f.png)

5.  Add a bot user by selecting the "Add Bot" button on the right of the window. Note that *"This action is irreversible (because robots are too cool to destroy)."*

![image](https://user-images.githubusercontent.com/46659572/160955121-3b4f6dc6-b144-40a5-ade3-0eecaf5d25ed.png)

6.  Once the bot user has been created, feel free to set the icon, rename the bot, and/or alter any settings that are applicable to your scenario

7.  **Important:** Discord bots each have their own unique token. To get your bot's token, select the "Reset Token" button just to the right of your bot's icon. This may prompt you to enter a 2-factor authentication code. If it does not, I recommend turning on 2FA for your Discord account to increase security. More information on this can be found here (https://support.discord.com/hc/en-us/articles/219576828-Setting-up-Two-Factor-Authentication)

![image](https://user-images.githubusercontent.com/46659572/160955437-d012790a-cbdf-42ca-9ee4-bc33c86bcdad.png)

8.  This token can be copied to your clipboard and implemented into the bot. If this is not done, the bot will not function

**Note:** Discord will automatically reset your bot's token, should it be discovered on the internet

***Setting Up in IntelliJ IDEA (Not/Semi Applicable to other Java IDEs)***

1.  Open IntelliJ IDEA

2.  In the top right of the window, select the button that reads "Get from VCS"

![image](https://user-images.githubusercontent.com/46659572/160950778-6ca3b26d-845b-4724-b185-34c6de8d5aba.png)

3.  Select GIT as the version control method, paste the URL of this repository in the box labeled "URL," and select the directory this project will be located in

![image](https://user-images.githubusercontent.com/46659572/160951095-127e10eb-3b81-4ed1-9d11-0e73735e18d5.png)

4.  Trust or preview the project as desired (trusting will be the only way to advance)

5.  This README will be the first thing that appears. Hi!

      5a. The classes that make up Bro Magnum can be found at this directory:
      
          ...\BroMagnumBot-Tutorial\src\main\java
      
6.  **Important:** Bro Magnum requires one additional file that is *not* included in this repository. It will not build nor run properly without this

      6a. Right-click on the "java" folder and select "New > Java Class"
      
      6b. Name the new class "Resources"
      
      6c. The content of the "Resources" class should be the following:
      
          public class Resources {
            String token = "<bot_token>";
            String flag = "<flag>";
          }
     
      6d. Populate the "token" and "flag" fields with values that are appropriate for your use. See ***Creating a Discord Bot*** for more information about Discord bot tokens
      
      6e. Build the project to assure that this new file has been created and implemented correctly

7.  Test that the program by running the Main file that can be found at this directory:

          ...\BroMagnumBot-Tutorial\src\main\java\Main.java
      
      7a. To run the program, open the file and select the green run button to the left of the class header, then select "Run 'Main.main()'"
      
      ![image](https://user-images.githubusercontent.com/46659572/160953851-27e226a0-d430-4be7-8ca9-4909663b9228.png)
      
      7b. If this window appears, the program has been set up successfully:
      
      ![image](https://user-images.githubusercontent.com/46659572/160954095-5920613c-5d24-411d-b0ad-1cfa53bc7861.png)

***Using the Interface***

1.  Open the BroMagnum.jar file with Java Runtime Environment

2.  When opened, Bro Magnum will connect to Discord servers and appear online
      
      2a. There may be a scenario where this no longer works. Discord bots each have a unique token and, should that token be reset either manually or automatically, your version of the application will cease to function. If this happens, a new artifact must be built with the correct token
      
      2b. The current token for Bro Magnum cannot be found online as Discord will automatically reset its token, should it be discovered on the internet

3.  While the application is open, the bot is online and will remain that way until interrupted
      
      3a. The console output is viewable in the 'Console Out' text area. There is no option for input
          
          When the following message appears, the bot has successfully connected to the Discord servers:
            [JDA MainWS-ReadThread] INFO JDA - Finished Loading!
      
      3b. There are two buttons on the bottom right of the window
          
          Restart - Will disconnect the bot from the Discord servers and reconnect it automatically
          
          Close - Will disconnect the bot from the Discord servers and close the application
      
      3c. There is a list of commands on the bottom left of the window that can be used in a direct message (on Discord) with this bot
          
          !ping - Will ping the bot and return its response time
          
          !restart_bro - Will restart the bot remotely, without the need to use the application

4. Any action that closes the application will disconnect the bot from the Discord servers

***To Invite Bro Magnum to Your Server***

1. Contact ChristianR839 for an invite link

2. Open the link and allow the desired permissions

3. The bot will join the server you direct it to and, as long as it is online, will fulfill its purpose

**Note:** If you want the existence of Bro Magnum in your server to be a secret, be sure to change the System Messages Channel on your server *OR* disable the "Send a random welcome message when someone joins the server" setting. Both options can be found at Your Server > Server Settings > Overview > System Messages Channel
