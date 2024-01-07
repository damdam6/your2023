import React from 'react';
import styled from 'styled-components';
import ImgBox from './img-box';

const URL: string[] = [
  'https://images.unsplash.com/photo-1703593693062-16aab101121a?q=80&w=886&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  'https://images.unsplash.com/photo-1703555508141-4397207fc6d2?q=80&w=1032&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  'https://images.unsplash.com/photo-1703750960118-1c49d4a9fa78?q=80&w=580&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  'https://images.unsplash.com/photo-1701743801469-57e71ae3da6c?q=80&w=928&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  'https://images.unsplash.com/photo-1649290098499-f4148542f2e0?q=80&w=928&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  'https://images.unsplash.com/photo-1698853983454-7e819026af6c?q=80&w=928&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  'https://images.unsplash.com/photo-1703870977610-de36beca1d68?q=80&w=988&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
];

export interface InterImgBox {
  src: string;
}

const ImageContainer = styled.div`
  height: 25%;
  padding: 1%;
`;



const Slide: React.FC = () => {
  return (
    <ImageContainer>
      <div className="flex flex-row w-full h-full animate-slider">
        {URL.map((imgUrl, index) => (
          <ImgBox key={index} src={imgUrl} />
        ))}
      </div>
    </ImageContainer>
  );
};

export default Slide;
