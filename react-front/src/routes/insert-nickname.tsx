import React, { useState } from "react";
import InsertText from "../components/insert/nickname/insert-text";
import ExplainNickname from "../components/insert/nickname/explain-nickname";

const InsertNickname = () => {
  return (
    <div>
      <ExplainNickname />
      <InsertText />
    </div>
  );
};

export default InsertNickname;
