export default function PhotoCard() {
  return (
    <div className="flex flex-col items-center justify-center">
      <div className="relative w-96">
        <div className="absolute z-30 flex p-4 bg-gray-100 rounded-lg drop-shadow-md">
          <img
            className="h-auto rounded-lg w-96"
            src="https://images.unsplash.com/photo-1682686580849-3e7f67df4015?q=80&w=1740&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            alt="Image"
          />
        </div>
      </div>
    </div>
  );
}
