import styled from 'styled-components';

import ButtonNextPage from "./button-next-page";
import TitleText from "./title-text";

const Wrapper = styled.div`
  display: flex;
  flex-direction: row;
  height: 50%;
  padding: 0 5%;
  justify-content: space-around;
`;

export default function Title() {
  return (
    <Wrapper>
      <TitleText />
      <ButtonNextPage />
    </Wrapper>
  );
}
