import styled from 'styled-components';

import Wrapper from './Wrapper';

const TITLE_TEXTS = ['당신의 2023년을', '하나의 이미지로', '만들어보세요!'];

const Title = styled.h1`
  font-size: 10vh;
  font-weight: 900;
`;

const titles = TITLE_TEXTS.map((text, index) => (
  <Title key={index}>{text}</Title>
));

export default function TitleText() {
  return <Wrapper>{titles}</Wrapper>;
}
