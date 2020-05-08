# Mouthpiece Converter Main Branch
Android Mouthpiece Application (Omega)

## Project Management Tool
https://app.clubhouse.io/cos-301-team-converter/stories/space/9/everything

## Profiles
[Pierre du Plessis](https://endlessprogrammer.github.io/pierregduplessis.github.io/)<br/>
[Phillip Schulze](https://phillipstemmlar.github.io)<br/>
[Steven Visser](https://vanillav.github.io/)<br/>
[Munashe Mujaji](https://munasheghub.github.io/)<br/>
[Obakeng Seageng](http://obakengseageng.github.io/)<br/>
[Nthabiseng Tia Mangena](https://tiamangena.github.io/)<br/>
[Vedha Krishna Velthapu](https://vedha286.github.io/)<br/>

## Repository Layout
This is how the converter branch is futher branched
```
master 
└── converter
    └──── Pierre_Interface_patch-1 (Fullscreen function with motion detection)
    └──── Munashe_voicetraining_patch-1
    └──── Obakeng_Sound_Sensitivity_Patch-1
```

## Contributions
### Pierre du Plessis
- Did the animation for the open and close mouth formations
- Created the volume conversion functions for the basic recording.
- Integrated the AI functions which only returned null.
- Added fullscreen and motion sensor functions.

### Steven Visser
- Coded the animation functions for each of the following formations:
	(A,L,O,C,F,Q,B,U,Ee,R,Th,Ch)
- Created the animation function that calls the Neural Network and then 
calls the required animation function with the output from the Neural 
Network.
- Implemented the OnClick function for the formant button to transfer audio
segments to the Neural Network Module.

### Tia Mangena
- For the converter she did the animations for L and aei formations. 
- She also did unit testsing for this module.
- Helped to integrating the AI module

### Munashe Mujaji
#### Part 1
- Created mouth animations (XML files) for Q, W and Th formations

#### Part 2
(Uploaded under Munashe_voicetraining_patch-1 branch)
- Voice profile interface
- Function to save recorded voice profile (up to 4) to device storage
- Mock function to send audio files to Neural Network for analysis

### Obakeng Seageng
- Created Record Stop Function
- Coded the Animation of the Mouth formations F & V 
- Optimized recording by choose best sensitivity numbers & reduced noise from background

### Phillip Schulze
- Coded the animation functions for each of the following formations:
	(E,C,D,K,N,S,T,X,Y,Z)
- Fixed crashing problem
- Added a autorotate function

### Vedha Krishna
- Worked on changing VoiceProfile_Trainer.java's audio functionality
- Coded the Animation of the Mouth formations j, q, u 
- Lower speaking volume causes animation of mouth to open slightly

## Guidelines
Here are a couple of guidelines for this repo
  - Create a branch from your module and work on that (e.g. Neural Network member will branch from neuralnetwork branch)
  - Please don't push to any other branch other than your own or its branches.
  - Don't push to master, development, or any other branch that's unrelated to your module.
  - REMEMBER: **PULL BEFORE PUSH**
  
 # Profiles:

[Kevin Huang](https://kevin-d-h.github.io/)

[Ronald Looi](https://rsl-student.github.io/)

[Tebogo Selahle](https://tebogo.codes/)

[Anrich Hildebrand](https://anrich96.github.io/)

[Anika Van Rensburg](https://anikavanrensburg.github.io/)

[Shaun Vermeulen](https://ShaunVermeulen.github.io)


# CV Directories

Neural Network CV: Neural Network Branch
Notification CV: Notification Branch
Convertor CV: Convertor Branch
User Management API CV: User-Management-API repository
Sharing API CV: Sharing-API repository
Sharing Web CV: Sharing-Web repository

# Project management tools (Clubhouse):

Integration:
[Link](https://app.clubhouse.io/invite-link/5e5ea501-0921-4373-925f-6cef220c9aaf)

User Management:
[Link](https://app.clubhouse.io/invite-link/5e681181-b82c-4197-b3cf-7aaffeb61d16)

Converter:
[Link](https://app.clubhouse.io/invite-link/5e67d027-f0e5-4307-8f82-edb2479c7153)

Web-Sharing:
[Link](https://app.clubhouse.io/invite-link/5e674a86-61ed-4a64-bfa4-73b1720eb82e)

Sharing-API:
[Link](https://app.clubhouse.io/invite-link/5e690707-5704-420c-bebb-4b8cc516ab7b)


# Slack:
[Link](https://join.slack.com/t/omega301/shared_invite/zt-ck83m1ly-cTjyhJ9DdJb9kyQRmLJdQw)


# Other GitHubs:

Sharing Web:
[Link](https://github.com/CarelKemp/COS301_OmegaMouthPieceWeb)

Sharing API:
[Link](https://github.com/ShaunVermeulen/OmegaSharingAPI)

User Management API:
[Link](https://github.com/Kevin-D-H/Omega-User-Management-API)
