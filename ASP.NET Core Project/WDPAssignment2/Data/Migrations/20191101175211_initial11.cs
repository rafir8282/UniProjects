using Microsoft.EntityFrameworkCore.Migrations;

namespace WDPAssignment2.Data.Migrations
{
    public partial class initial11 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "canIncreaseDislike",
                table: "MachineLearningCompaniesFeedback");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<bool>(
                name: "canIncreaseDislike",
                table: "MachineLearningCompaniesFeedback",
                type: "bit",
                nullable: false,
                defaultValue: false);
        }
    }
}
