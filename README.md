# Speech-to-Text Java Library (SDK)

# Description
- Speach to text java SDK.
- Converts stream audio into text.

# Prerequisites
-  JAVA JDK 8 or above.
-  Make sure your are connected with VPN.

# How to add Library into Project Structure
- To use Speech-to-Text Java library you need to add into project structure
- Create a sample project.
- Goto (File->Project Structure -> Library -> click on **+** button -> select the library -> Apply->Ok)


# How to use Library
- Create instance of **AcceptAudio** class by passing samplerate as a constructor agrument.
    - What is samplerate?
    - Samplerate is an integer type of variable.What type of samplerate stream you are passing pass this value as a sample rate.
- **AcceptAudio** contains send method.Using send method you can pass your audio stream to library.
   
# Sample code to use Library
- AcceptAudio audio=new AcceptAudio(60000);
- audio.send(buffer[]);

**Note:**
- 60000 is samplerate.
- buffer is an array of streaming audio data.

# How to get Result from library
- STT library provide a class called **ResultNotifiable**.
- **ResultNotifiable** class contains events namely.
  - OnPartialText()
  - OnResultText()

# How to use ResultNotifiable class
- This is an abstract class which having two callback methods.
- Create a class and extends **ResultNotifiable** class and use provided method as per our requirement.

# Code Snippet to use ResultNotifiabl
Class Result extend ResultNotifiable{

    /**
    * It will auto override the two methods
    *  OnPartialText()
    *  OnResultText()
    **/
}


