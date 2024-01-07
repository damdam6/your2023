export default function PhotoCard() {
  return (
    <div className="flex flex-col items-center justify-center">
      <div className="relative w-96">
        <div className="absolute z-30 flex p-4 bg-gray-100 rounded-lg drop-shadow-md">
          <img
            className="h-auto rounded-lg w-96"
            src="https://s3.ap-northeast-2.amazonaws.com/bucket-your2023-image/images/damongsanga_generatedImage_1704028561535"
            alt="Image"
          />
        </div>
      </div>
    </div>
  );
}
