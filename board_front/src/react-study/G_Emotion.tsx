import { css, Global } from '@emotion/react'
import styled from '@emotion/styled';
import React from 'react'
/** @jsxImportSource @emotion/react */

const divStyle = css`
background-color: #007bff;
color: white;
margin: 4px;
padding: 8px 16px;
border: none;
border-radius: 4px;
&:hover {
  background-color: #0056b3;
}
`;

const StyledDiv = styled.div`
  background-color: #007bff;
  color: white;
  margin: 4px;
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  &:hover {
  background-color: #0056b3;
}
`;

const buttonStyle = (status: boolean) => css`
  background-color: ${status? 'green': 'red'};
  color: white;
`;

const globalStyles = css`
  body{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    background-color: #d2ff9b;
  }
`;

export default function G_Emotion() {
  return (
    <>
    <Global styles = {globalStyles} />
      <div css={divStyle}>
      HELLO, EMOTION1
    </div>
    <StyledDiv>
    HELLO, EMOTION2
    </StyledDiv>
    <hr />

    <button css={buttonStyle(true)}>Hello Dynamic Emotion</button>
    <button css={buttonStyle(false)}>Hello Dynamic Emotion</button>
    </>
  )
}
