import { useState } from "react";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import Layout from "./components/layout";
import Home from "./routes/home";
import InsertPhoto from "./routes/insert-photo";
import Result from "./routes/result";
import InsertNickname from "./routes/insert-nickname";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    children: [
      {
        path: "",
        element: <Home />,
      },
      {
        path: "/insert/photo",
        element: <InsertPhoto />,
      },
      {
        path: "/insert/nickname",
        element: <InsertNickname />,
      },
      {
        path: "/result",
        element: <Result />,
      },
    ],
  },
]);

function App() {
  return (
    <>
      <RouterProvider router={router} />
    </>
  );
}

export default App;
