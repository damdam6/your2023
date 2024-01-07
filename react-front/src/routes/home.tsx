import styled from 'styled-components';

import Slide from '../components/home/scroll/slide';
import Title from '../components/home/title/Title';

const Wrapper = styled.div`
  height: 100%;
`;

export default function Home() {
  return (
    <Wrapper>
      <Slide />
      <Title />
      <Slide />
    </Wrapper>
  );
}
