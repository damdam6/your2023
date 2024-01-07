import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getStorage } from "firebase/storage";
import { getFirestore } from "firebase/firestore";

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyC1PEsrBd1tfwc7x8i-sLKFrwkdl6tY7TA",
  authDomain: "your2023.firebaseapp.com",
  projectId: "your2023",
  storageBucket: "your2023.appspot.com",
  messagingSenderId: "669616568928",
  appId: "1:669616568928:web:78116ee9ce7f5b9e6d6105",
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

export const auth = getAuth(app);

export const storage = getStorage(app);
export const db = getFirestore(app);
