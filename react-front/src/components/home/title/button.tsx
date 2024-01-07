import { useNavigate } from "react-router-dom";

export default function Button() {
  const navigate = useNavigate();
  const onClick = () => {
    navigate("/insert/nickname");
  };
  return (
    <div className="inline-block mt-2 mr-2">
      <button
        type="button"
        onClick={onClick}
        className="focus:outline-none text-blue-600 text-sm py-2.5 px-5 rounded-md border border-blue-600 hover:bg-blue-50"
      >
        START
      </button>
    </div>
  );
}
