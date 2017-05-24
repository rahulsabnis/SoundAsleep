# SoundAsleep

## Description
An Android application that uses songs to help students fall asleep, developed for the UCSD Application Student Innovation Contest.

## How to Run

1. Transfer soundasleep.apk to your android phone
2. Open the apk file and install the app
3. Launch the app from your application drawer

## User Stories

The following functionality is completed:

- [✔] Users can listen to music from the app (Spotify Premium membership required)
- [✔] Users can control the current song (Play, Pause, Previous, Next)
- [✔] Users can view the album artwork (You should probably be asleep but you never know!)
- [✔] Users can select a time for the music to last after which it will stop

The following functionality is planned for the future:

- [ ] A greater selection of music
- [ ] Tracking via health sensors & smart devices to personalize music
- [ ] Allow for non-Spotify Premium subscribers to access music
- [ ] Improve the User Interface

## Technical Challenges

My biggest technical challenge was figuring out how to play the music. I tried using Soundcloud's APIs, looked into custom solutions, but finally decided upon Spotify's Android SDK. This ended up being the easiest to implement and had the greatest set of features. Unfortunately, this did mean that I needed to restrict access to playing music to Spotify Premium users, but it was the price I had to pay to get the first version out there.

## Why Music?

From the days when we were infants, music has always helped us relax and feel calmer. My mother would put me to sleep with various lullabies that were so soothing and according to her, always helped me sleep. That got me thinking that if music was so effective on young children, why not try to use it on college students. Thus, the idea was born: a music app for college students that helps them sleep via music. To go about this, I didn't just want to pick out songs designed for children, but instead wanted the songs that teenagers and young adults listen to most. I found lullaby versions of many top pop songs released within the last few years. This makes the experience more pleasant for and adult while still preserving the effect.

## Evidence

When asked why music puts people to sleep, Lyz Cooper from the British Academy of Sound Therapy stated that:

“I think there are two ways of looking at this, Music that is designed to relax us does this through repetition of rhythms, music phrases, slow tempo and low tones. Music that has positive memory association can also help, so music that reminds us of a relaxed and happy time”

