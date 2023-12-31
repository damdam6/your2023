import React from "react";

interface ImgBoxProps {
  src: string;
}

const ImgBox: React.FC<ImgBoxProps> = ({ src }) => {
  return (
    <div>
      <img className="object-cover w-auto m-10 h-96" src={src} alt="Image" />
    </div>
  );
};

export default ImgBox;
