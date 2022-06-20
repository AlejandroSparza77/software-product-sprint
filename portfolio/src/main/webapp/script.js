// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/** Fetches a hardcoded string and adds it to the page. **/
async function showServerString() {
    //Send request:
    const responseFromServer = await fetch('/hello');
    
    //const textFromResponse = await responseFromServer.text();
    
    // Parse the response as JSON.
    const textFromResponse = await responseFromServer.json();

    const stringContainer = document.getElementById('string-container');
    
    const jsonSize = Object.keys(textFromResponse).length;
    var randomNumber = Math.floor(Math.random() * jsonSize); //Random number between
        //0 and 2 (inclusive).
    var counter = 0; //Index to get to the random number.
    var response = ""; //Random string of response.
    var randomKey = Object.keys(textFromResponse)[randomNumber];

    response = textFromResponse[randomKey];

    // Now we can reference the fields:
    for (const i in textFromResponse) {
        console.log(textFromResponse[i]);
    }

    //stringContainer.innerText = textFromResponse;
    stringContainer.innerText = response;

}

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      //['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!'];
        ['D\'oh!', 'I love to play music!', 'I\'m studying Computer Science'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}
