import Slide from "../components/home/scroll/slide";
import ButtonNextPage from "../components/home/title/button-next-page";
import TitleText from "../components/home/title/title-text";

export default function Home() {
  return (
    <div>
      <Slide />
      <div className="flex flex-row">
        <TitleText />
        <ButtonNextPage />
      </div>
      <Slide />
    </div>
  );
}
