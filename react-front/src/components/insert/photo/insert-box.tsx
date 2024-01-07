import InsertCard from "./insert-card";
import React, { useState, useCallback, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function InsertBox() {
  const [formData, setFormData] = useState(() => new FormData());
  const navigate = useNavigate();
  const handleFileAdd = useCallback(
    (file: File, id: number) => {
      // 새로운 FormData 객체를 만들어서 기존 데이터와 새 파일을 추가
      const fileKey = `image${id}`;
      const newFormData = new FormData();
      for (const entry of formData.entries()) {
        newFormData.append(entry[0], entry[1]);
      }
      newFormData.append(fileKey, file);

      // 업데이트된 FormData 객체로 상태 업데이트
      setFormData(newFormData);
    },
    [formData]
  );

  //콘솔 프린트용;
  useEffect(() => {
    for (const [key, value] of formData.entries()) {
      console.log(key, value);
    }
  }, [formData]);

  const sendData = async function sendData(formData: FormData) {
    try {
      const response = await axios.post("api주소", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      console.log("Response:", response.data);
    } catch (error) {
      console.error("Error sending data:", error);
    }
  };

  return (
    <div>
      <div className="flex flex-row flex-wrap">
        <InsertCard id={1} onFileAdd={(file) => handleFileAdd(file, 1)} />
        <InsertCard id={2} onFileAdd={(file) => handleFileAdd(file, 2)} />
        <InsertCard id={3} onFileAdd={(file) => handleFileAdd(file, 3)} />
        <InsertCard id={4} onFileAdd={(file) => handleFileAdd(file, 4)} />
      </div>
      <button
        onClick={() => {
          sendData(formData);
          navigate("/result");
        }}
        className="submit-button"
      >
        Submit
      </button>
    </div>
  );
}
