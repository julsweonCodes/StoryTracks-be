# About StoryTracks
### 🤔💭 How we came up with the idea
<img src="/imgs/1.png" width="50%" height="auto">
<img src="/imgs/2.png" width="50%" height="auto">
<img src="/imgs/3.png" width="50%" height="auto">

### About this app

StoryTracks is a mobile-centered web application designed to capture and share personal stories through location-based posts. Users can upload pictures from their device, and with the help of metadata like location and time, along with text input or voice-to-text, create unique posts. 

The app uses the Gemini API to automatically generate engaging content for each post. StoryTracks also includes features like user authentication (login and logout), a map feed powered by the Google Maps API to explore posts based on location, and the ability to react to others' stories. Perfect for sharing memories, experiences, and moments on the go!

Frameworks: Spring Boot(Backend), NextJS(Frontend), AWS(MySQL RDS, Amazon S3)
<br><br>

### 🧱 Repository Structure
StoryTracks is composed of three modular repositories under the same project umbrella:

[StoryTracks-fe](https://github.com/julsweonCodes/StoryTracks-fe)
<br>
Frontend built with Next.js. Handles UI/UX, client-side routing, and integration with Google Maps API and Gemini API.

[StoryTracks-be](https://github.com/julsweonCodes/StoryTracks-be)
<br>
Backend built with Spring Boot. Manages authentication, post processing, and business logic.

[StoryTracks-infra](https://github.com/julsweonCodes/StoryTracks-infra)
<br>
Infrastructure-as-Code and deployment settings. Includes AWS setup, database configuration, and CI/CD pipelines.

This structure allows each part of the application to be developed and maintained independently while ensuring smooth integration across services.

## 🥁 How to use our app
**🗺️📍 Explore posts based on your location!**

<img src="/imgs/4.png" width="30%" height="auto"><img src="/imgs/5.png" width="30%" height="auto">

<br>

**🔮✨ Share your posts with AI-generated text based on your photos!**

<img src="/imgs/6.png" width="30%" height="auto"><img src="/imgs/7.png" width="30%" height="auto">
<br><br>

Select photos that includes metadata

<img src="/imgs/8.png" width="auto" height="400px"><img src="/imgs/9.png" width="auto" height="400px">
<br><br>

Enter user content and generate AI text !

<img src="/imgs/10.png" width="25%" height="auto"><img src="/imgs/11.png" width="25%" height="auto"><img src="/imgs/12.png" width="25%" height="auto"><img src="/imgs/13.png" width="25%" height="auto">
<br><br>

## Future Updates 
StoryTracks is a project developed during the KATEC Hackathon as a Minimum Viable Product (MVP) of our idea. <br>While the core features, including location-based posts powered by Google Maps API and AI-generated content via the Gemini API, are fully implemented, other features like user authentication, reacting to posts, and commenting are still a work in progress.
<br><br>Feel free to use our project as a foundation and enhance it by adding new features, improving functionality, or building on the concept.🥳 <br>We’re excited to see how others can take this idea to the next level!
