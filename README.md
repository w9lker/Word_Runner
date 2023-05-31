# Word_Runner
Word Runner is a software developed by Amazon and integrated into their Kindle products for enhancing reading experience. It has been suggested that the technique employed by the app helps users increase their reading speed by a few magnititutes. Here I made my own implementation of an app for Windows users.
Word Runner basically shows one word a time and speeds up and down when reading to adjust for user's natural reading speed, since users can't sustain high speed for long time.
## Notes:
I am still working on a few bugs and features to implement

## Features:
* Supports in-app text input into designated area, .docx, .txt files (used Apache POI - API for working mainly with Office programs to extract text from .docx files)
* Has forward and backward buttons in case user wants to get back
* Natural timing is adapted (similarly to original Word Runner). Unlike original mine doesn't use comprehensive user data to predict where to slow down and up. But I made simple enough alternative that works for most cases. The app speeds up throughout the whole sentecen and resets the speed when starting new setence. It slows down at the end of the sentence, punctuation. 

## Further Impovement Ideas
* Support for .doc, .pdf files
* User-interactive windows to get back to any word in the text in case user wishes so

## How to get started?
1. clone the repository using
```
git clone https://github.com/w9lker/Word_Runner
```
2. The code has been tested to work on IntelliJ IDEA Community Edition 2023.1.1 
3. Enable JavaFX Plugin under **Settings -> Plugins**
4. Open the project
### P.S. Current bugs:
* FileUpload Button disappears after uploading a file

