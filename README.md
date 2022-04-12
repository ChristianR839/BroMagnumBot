# BroMagnumBot

Bro Magnum is a Discord bot that utilizes the JDA (Java Discord API) (https://github.com/DV8FromTheWorld/JDA). Its purpose is to provide WPI CS4401 students with a flag given they direct message (DM) the bot while it is online and certain conditions are met. Additional features have been added to increase the bot's activity in servers purely for entertainment.


## Requirements

• Java Runtime Environment 1.8.0 or higher (https://www.java.com/en/download/manual.jsp)

• IntelliJ IDEA 2021.3.3 or higher (https://www.jetbrains.com/idea/download/) or any other Java IDE of choice

**NOTE:** All screenshots are taken from a device running Windows 10. Bro Magnum can run on other desktop operating systems, such as MacOS.


## Creating a Discord Bot

1.  Navigate to the Discord Developer Portal > Applications page (https://discord.com/developers/applications). This will require logging in with a Discord account. If you do not have one, create one to proceed
      
2.  Select the `New Application` button at the top right of the window next to your user icon

![image](https://user-images.githubusercontent.com/46659572/160954773-57cc11c0-a218-44f6-975f-f5c218da374c.png)

3.  Create a new application, giving it any name you choose. This can be changed later

![image](https://user-images.githubusercontent.com/46659572/160954873-4cbf1e61-27f9-4b5d-bfc0-56bee27cedcc.png)

4.  Your application has been created, but it is not a bot just yet. Navigate to the `Bot` tab on the left side of the window

![image](https://user-images.githubusercontent.com/46659572/160955005-81d1558f-2690-48c2-9dcc-53335423543f.png)

5.  Add a bot user by selecting the `Add Bot` button on the right of the window. Note that *"This action is irreversible (because robots are too cool to destroy)."*

![image](https://user-images.githubusercontent.com/46659572/160955121-3b4f6dc6-b144-40a5-ade3-0eecaf5d25ed.png)

6.  Once the bot user has been created, feel free to set the icon, rename the bot, and/or alter any settings that are applicable to your scenario

7.  **Important:** Discord bots each have their own unique token. To get your bot's token, select the `Reset Token` button just to the right of your bot's icon. This may prompt you to enter a 2-factor authentication code. If it does not, I recommend turning on 2FA for your Discord account to increase security. More information on this can be found here (https://support.discord.com/hc/en-us/articles/219576828-Setting-up-Two-Factor-Authentication)

![image](https://user-images.githubusercontent.com/46659572/160955437-d012790a-cbdf-42ca-9ee4-bc33c86bcdad.png)

8.  This token can be copied to your clipboard and implemented into the bot. If this is not done, the bot will not function

**Note:** Discord will automatically reset your bot's token, should it be discovered on the internet


## Setting Up in IntelliJ IDEA (Not Applicable to other Java IDEs)

1.  Open IntelliJ IDEA

2.  In the top right of the window, select the button that reads `Get from VCS`

![image](https://user-images.githubusercontent.com/46659572/160950778-6ca3b26d-845b-4724-b185-34c6de8d5aba.png)

3.  Select GIT as the version control method, paste the URL of this repository in the box labeled `URL`, and select the directory this project will be located in

![image](https://user-images.githubusercontent.com/46659572/160951095-127e10eb-3b81-4ed1-9d11-0e73735e18d5.png)

4.  Trust or preview the project as desired (trusting will be the only way to advance)

5.  This README will be the first thing that appears. Hi!

      5a. The classes that make up Bro Magnum can be found at this directory:
      
          ...\BroMagnumBot-Tutorial\src\main\java
      
6.  **Important:** Bro Magnum requires one additional file that is *not* included in this repository. It will not build nor run properly without this

      6a. Right-click on the `java` folder and select `New > Java Class`
      
      6b. Name the new class `Resources`
      
      6c. The content of the `Resources` class should be the following:
      
          public class Resources {
            String token = "<bot_token>";
            String flag = "<flag>";
            String wordleFile = "<filename>";
          }
     
      6d. Populate the `token`, `flag`, and `filename` fields with values that are appropriate for your use. See ***Creating a Discord Bot*** for more information about obtaining your bot's token
      
      6e. Build the project to assure that this new file has been created and implemented correctly

7.  Choose your preference for running Bro Magnum: GUI or command line. Bro Magnum has a GUI that can be switched on manually by modifying the `gui` boolean in `Main.java`
          
          // Set to true if the GUI is desired
          boolean gui = false;
    
    **NOTE:** The GUI is updated less-frequently alongside new features. Desired actions may not be possible on some versions

8.  Test that the program by running the Main file that can be found at this directory:

          ...\BroMagnumBot-Tutorial\src\main\java\Main.java
      
      8a. To run the program, open the file and select the green run button to the left of the class header, then select `Run 'Main.main()'`
      
      ![image](https://user-images.githubusercontent.com/46659572/160953851-27e226a0-d430-4be7-8ca9-4909663b9228.png)
      
      8b. For command line users: If your console output declares that the bot has been connected to servers, the program has been set up successfully:
      
          SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
          SLF4J: Defaulting to no-operation (NOP) logger implementation
          SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
          SLF4J: Failed to load class "org.slf4j.impl.StaticMDCBinder".
          SLF4J: Defaulting to no-operation MDCAdapter implementation.
          SLF4J: See http://www.slf4j.org/codes.html#no_static_mdc_binder for further details.
          [main] INFO JDA - Login Successful!
          [BOT] Bot connected to servers.
          [APP] Use 'bm-restart' to disconnect from and reconnect to servers.
          [APP] Use 'bm-wordle' to create a new input file for wordles.
          [JDA MainWS-WriteThread] INFO WebSocketClient - Connected to WebSocket
          [JDA MainWS-ReadThread] INFO JDA - Finished Loading!
          
      See ***Running in the Commadn Line*** for more information about commands
      
      8c. For GUI users: If this window appears and console output declares that the bot has been connected to servers, the program has been set up successfully:
      
      ![image](https://user-images.githubusercontent.com/46659572/162989760-51356e3c-7993-4517-9e75-baee441e07be.png)


## Creating an Executable Jar in IntelliJ IDEA (Not Applicable to other Java IDEs)

1.  Navigate to `File > Project Structure`

2.  Under the `Project Settings` header, select `Artifacts`

3.  If something is already in the list as pictured below, no action is required as long as the settings are as explained in step 4:

![image](https://user-images.githubusercontent.com/46659572/160957276-25ad78dd-43ed-402b-ae10-cf277927d6db.png)

4.  To create a new artifact, select the plus (+) button above the list pictured above. Choose `JAR > From modules with dependencies...`

5.  Set the Main Class to `Main.java` and any other preferences

![image](https://user-images.githubusercontent.com/46659572/160957649-2c0ece46-93a1-46aa-9e88-1e478995bee3.png)

6.  Be sure to select `Apply` / `OK` to save your changes

7.  Navigate to `Build > Build Artifacts... > Build` to build the executable jar

8.  There should now be an executable jar at this directory (or one similar, due to naming differences):

          ...\BroMagnumBot-Tutorial\out\artifacts\BroMagnum_jar\BroMagnumBot.jar
          
9. For command line users: See ***Running in the Command Line*** to test for functionality
          
10. For GUI users: open the executable jar to test that it runs. If this window appears, the executable jar has been created successfully:

![image](https://user-images.githubusercontent.com/46659572/162989760-51356e3c-7993-4517-9e75-baee441e07be.png)


## Running in the Command Line

1.  Open a command line interface on your device

    To open a Windows Command Prompt, use `Win + R` to open the Run window and type in `cmd` and select `OK`. For more information on how to use the Command Prompt, see this article (https://docs.microsoft.com/en-us/windows-server/administration/windows-commands/windows-commands).
    
    To open a MacOS Terminal, click the Launchpad icon in the Dock, type `Terminal` in the search field, then click `Terminal`. In the Finder , open the `/Applications/Utilities` folder, then double-click `Terminal`. For more information on how to use the Terminal, see this article (https://support.apple.com/guide/terminal/execute-commands-and-run-tools-apdb66b5242-0d18-49fc-9c47-a2498b7c91d5/mac)

2.  Navigate to the directory of the executable jar that has been created

3.  Use the following java command to run the program:

          java -jar BroMagnumBot.jar

4.  If your console output declares that the bot has been connected to servers, the program is running successfully:
      
          SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
          SLF4J: Defaulting to no-operation (NOP) logger implementation
          SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
          SLF4J: Failed to load class "org.slf4j.impl.StaticMDCBinder".
          SLF4J: Defaulting to no-operation MDCAdapter implementation.
          SLF4J: See http://www.slf4j.org/codes.html#no_static_mdc_binder for further details.
          [main] INFO JDA - Login Successful!
          [BOT] Bot connected to servers.
          [APP] Use 'bm-restart' to disconnect from and reconnect to servers.
          [APP] Use 'bm-wordle' to create a new input file for wordles.
          [JDA MainWS-WriteThread] INFO WebSocketClient - Connected to WebSocket
          [JDA MainWS-ReadThread] INFO JDA - Finished Loading!

5.  Bro Magnum has two commands that can be used in the command line:

          bm-restart - Will disconnect the bot from and reconnect the bot to Discord servers
          
          bm-wordle - Will create a new file for your custom wordles, if it does not yet exist


## Using the Interface

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
      
      3c. There is a list of commands on the bottom left of the window that can be used with this bot:
          
      In a direct message on Discord:
          
          !ping - Will ping the bot and return its response time
          
          !restart_bro - Will restart the bot remotely, without the need to use the application
      
      In a server *or* a direct message on Discord:
      
          !cat - Will respond with a random image of a cat
          
          !meme - Will respond with a random meme from the r/ProgrammerHumor subreddit
          
          !comic - Will respond with a random garfield comic
      
          !!new_feats_message!! - Will delete the message containing the command,
          and immediately respond with a message explaining its new features to the server:
              
            hey, @everyone, just want to let you know that i have some new commands!
               • !cat - receive a random image of a cat\n"
               • !meme - receive a random meme from the r/ProgrammerHumor subreddit
               • !comic - receive a random comic of your favorite lasagna-loving monday-hating feline
            also, look forward to daily walldles (custom wordles for CS 4401) at noon!

4.  Any action that closes the application will disconnect the bot from the Discord servers


## Custom Wordles

1.  Bro Magnum is capable of posting custom wordles every day at noon, should you provide it with them. To prepare the bot, use the `bm-wordle` command to create a text file that will contain your wordles. It will have the same file name and extension as the string in the `wordleFile` field in `Resources.java`

2.  Bro Magnum uses Strivemath's Custom Wordle website for its custom wordles. Each line in this text file must be manually translated from this site (https://mywordle.strivemath.com/). For example: If I want the first three wordles to be `apple`, `boats`, and `crane` then I will navigate to the site and create custom URLs for them as seen below:

![image](https://user-images.githubusercontent.com/46659572/162997495-5ca74da2-2e86-44fc-8a5d-a1ea10c97431.png)

I will then include the string after `?word=` in the URL as a line in the wordle text file such that it will look like this:

        wdgop
        xcrwd
        yfrqp
        
3.  The bot will use the top-most word from the list each day, and append a `#` to the front of it when it has been used, marking it as unuseable. Any duplicates that may exist further down the list will also receive a `#`, assuring that each custom wordle is different

4.  If this has been set up correctly, the bot will automatically post a new wordle in the default channel of your server at noon EST/EDT (this can be changed in the code


## To Invite Bro Magnum to Your Server

1. Navigate to the `Discord Developer Portal > Applications` page (https://discord.com/developers/applications) and select your bot

2. Navigate to `OAuth2 > URL Generator`

![image](https://user-images.githubusercontent.com/46659572/160958304-3a0167f2-830d-4958-ad0c-4bd938b61d71.png)

3.  To create an invite link, your bot needs to know what scopes are required. Check the following boxes:

        bot
  
        applications.commands

4.  Then, select the permissions that the bot will need. A typical setup may include:

        Read Messages/View Channels
        
        Send Messages
        
        Send Messages in Threads
        
        Embed Links
        
        Attach Files
        
        Read Message History
        
        Add Reactions
        
5.  Once your desired permissions have been selected, copy the URL that is generated at the bottom of the window

6.  Upon opening it, Discord will prompt you to invite your bot to a server in which you have permission to do so

7. The bot will join the server you direct it to and, as long as it is online, will fulfill its purpose

**Note:** If you want the existence of Bro Magnum in your server to be a secret, be sure to change the System Messages Channel on your server *OR* disable the "Send a random welcome message when someone joins the server" setting. Both options can be found at Your Server > Server Settings > Overview > System Messages Channel
