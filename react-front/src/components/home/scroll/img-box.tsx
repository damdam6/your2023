import React from "react";
import styled from "styled-components"

const Image = styled.img`
  height: 100%;
  aspect-ratio: 1;
  object-fit: cover;
  margin: 0 1%;
`

interface ImgBoxProps {
  src: string;
}

const ImgBox: React.FC<ImgBoxProps> = ({ src }) => {
  return (
      <Image src={src} alt="Image" />
  );
};

export default ImgBox;
