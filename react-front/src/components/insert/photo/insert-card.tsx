import React, { useState } from "react";
import { getStorage, ref, uploadBytes, getDownloadURL } from "firebase/storage";

interface InsertCardProps {
  id: number;
  onFileAdd: (file: File) => void;
}

export default function InsertCard(props: InsertCardProps) {
  // ... (existing code)

  const onSaveFiles = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const uploadFiles = e.target.files;
    if (!uploadFiles || uploadFiles.length === 0) {
      return;
    }

    const file = uploadFiles[0];
    setFile(file);

    const storage = getStorage();
    const storageRef = ref(storage, `images/${file.name}`);

    try {
      await uploadBytes(storageRef, file);

      const imageUrl = await getDownloadURL(storageRef);
      setPreviewImage(imageUrl);
      props.onFileAdd(file);
    } catch (error) {
      console.error("Error uploading image:", error);
    },
    () => {
      // Complete function ...
      storageRef
        import { getDownloadURL } from "firebase/storage";

        // ...

        const imageUrl = await getDownloadURL(storageRef);
        .then(url => {
          setPreviewImage(url);
        });
    }
  };

  const squareClass = "w-52 h-52";

  return (
    <div
      className="flex justify-center w-full h-full p-4 m-2 bg-white rounded-lg shadow-lg max-w-64"
      style={{ width: previewImage ? "100" : "100%" }}
    >
      {previewImage ? (
        <img
          src={previewImage}
          alt="Preview"
          className="h-auto max-w-full rounded-lg"
        />
      ) : (
        <div
          className={`p-4 flex justify-center items-center transition duration-300 ease-in-out transform bg-gray-100 border-2 border-gray-300 border-dashed rounded-lg hover:border-blue-500 hover:scale-105 hover:shadow-md ${
            previewImage ? "max-w-full" : squareClass
          }`}
        >
          <label htmlFor={`fileInput-${props.id}`} className="cursor-pointer">
            <svg
              className="w-16 h-16 text-gray-400"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M12 6v6m0 0v6m0-6h6m-6 0H6"
              ></path>
            </svg>
          </label>
        </div>
      )}
      <input
        type="file"
        id={`fileInput-${props.id}`}
        className="hidden"
        multiple={false}
        accept="image/*"
        onChange={onSaveFiles}
      />
    </div>
  );
}
function setFile(file: File) {
  throw new Error("Function not implemented.");
}

function setPreviewImage(imageUrl: string) {
  throw new Error("Function not implemented.");
}
