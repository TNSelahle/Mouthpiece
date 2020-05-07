# Mouthpiece Converter Main Branch
Android Mouthpiece Application (Omega)

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
- ....
- Coded the Animation of the Mouth formations ...
- Fixed crashing problem
- Added a autorotate functiom

### Vedha Krishna
- Worked on changing VoiceProfile_Trainer.java's audio functionality
- Coded the Animation of the Mouth formations j, q, u 
- Lower speaking volume causes animation of mouth to open slightly

## Profiles
[Pierre du Plessis](https://endlessprogrammer.github.io/pierregduplessis.github.io/)<br/>
[Phillip Schulze](https://phillipstemmlar.github.io)<br/>
[Steven Visser](https://vanillav.github.io/)<br/>
[Munashe Mujaji](https://munasheghub.github.io/)<br/>
[Obakeng Seageng](http://obakengseageng.github.io/)<br/>
[Nthabiseng Tia Mangena](https://tiamangena.github.io/)<br/>
[Vedha Krishna Velthapu](https://vedha286.github.io/)<br/>

## Guidelines
Here are a couple of guidelines for this repo
  - Create a branch from your module and work on that (e.g. Neural Network member will branch from neuralnetwork branch)
  - **PULL BEFORE PUSH**
