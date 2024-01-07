import { useNavigate } from "react-router-dom";

export default function Button() {
  const navigate = useNavigate();
  const onClick = () => {
    navigate("/insert/nickname");
  };
  return (
    <div className="inline-block">
      <button
        type="button"
        onClick={onClick}
        className="focus:outline-none text-blue-600 text-7xl py-2.5 px-5 rounded-md border-4 border-blue-600 hover:bg-blue-50 w-96 h-36"
      >
        START
      </button>
    </div>
  );
}
