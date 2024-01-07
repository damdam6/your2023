import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const InsertText = () => {
  const [nickname, setNickname] = useState("");

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setNickname(event.target.value);
  };

  const navigate = useNavigate();
  const handleInsertClick = () => {
    // Insert logic here
    console.log("Insert button clicked");
    navigate("/insert/photo");
  };

  return (
    <div>
      <input
        type="text"
        id="nicknameInput"
        value={nickname}
        onChange={handleInputChange}
      />
      <button onClick={handleInsertClick}>Insert</button>
    </div>
  );
};

export default InsertText;
